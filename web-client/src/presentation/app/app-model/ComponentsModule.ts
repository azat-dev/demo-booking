import ProfileButtonVM, {
    ProfileButtonPresentation,
    ProfileButtonPresentationType
} from "../../components/navigation-bar/profile-button/ProfileButtonVM";
import Bus from "../../../domain/utils/Bus";
import ProfileButtonAnonymousViewModel
    from "../../components/navigation-bar/profile-button-anonymous/ProfileButtonAnonymousViewModel";
import ProfileButtonAuthenticatedViewModel
    from "../../components/navigation-bar/profile-button-authenticated/ProfileButtonAuthenticatedViewModel";
import AppSession, {SessionState} from "../../../domain/auth/entities/AppSession";
import AppSessionAuthenticated from "../../../domain/auth/entities/AppSessionAuthenticated";
import value from "../../utils/binding/value";
import AppSessionAnonymous from "../../../domain/auth/entities/AppSessionAnonymous";
import AppSessionLoading from "../../../domain/auth/entities/AppSessionLoading";
import NavigationBarViewModel from "../../components/navigation-bar/NavigationBarViewModel";
import Logout from "../../../domain/auth/commands/Logout";
import OpenLoginDialog from "../../commands/OpenLoginDialog";
import OpenSignUpDialog from "../../commands/OpenSignUpDialog";

class ComponentsModule {

    public constructor(
        private readonly appSession: AppSession,
        private readonly bus: Bus,
    ) {
    }

    public profileButton = (): ProfileButtonVM => {

        const sessionSubject = this.appSession.state;
        const toButton = (session: SessionState): ProfileButtonPresentation => {

            switch (session.type) {
                case AppSessionAuthenticated.TYPE:
                    return {
                        type: ProfileButtonPresentationType.AUTHENTICATED,
                        vm: this.authenticatedProfileButton(session as AppSessionAuthenticated)
                    };

                case AppSessionAnonymous.TYPE:
                    return {
                        type: ProfileButtonPresentationType.ANONYMOUS,
                        vm: this.anonymousProfileButton()
                    };
                case AppSessionLoading:
                    return {
                        type: ProfileButtonPresentationType.PROCESSING
                    };
                default:
                    throw new Error(`Unknown session state: ${session}`);
            }
        }

        const button = value(toButton(sessionSubject.value));
        sessionSubject.listen((session) => {
            button.set(toButton(session));
        });

        return new ProfileButtonVM(button);
    };

    public navigationBar = () => {
        return new NavigationBarViewModel(
            this.profileButton()
        )
    }

    private anonymousProfileButton = (): ProfileButtonAnonymousViewModel => {

        return new ProfileButtonAnonymousViewModel(
            () => {
                this.bus.publish(new OpenLoginDialog());
            },
            () => {
                this.bus.publish(new OpenSignUpDialog());
            }
        );
    }

    private authenticatedProfileButton = (session: AppSessionAuthenticated): ProfileButtonAuthenticatedViewModel => {

        const userInfo = session.userInfo;
        const fullName = value(userInfo.value.fullName);
        const email = value(userInfo.value.email);
        const photo = value(userInfo.value.photo);

        const disposables = [
            fullName,
            photo,
            email
        ];

        const button = new ProfileButtonAuthenticatedViewModel(
            fullName,
            email,
            photo,
            () => {
                this.bus.publish("open-profile-page");
            },
            () => {
                this.bus.publish(new Logout());
            }
        );

        const defaultDispose = button.dispose;

        button.dispose = () => {
            disposables.forEach((d) => d.dispose());
            defaultDispose();
        };

        return button;
    }
}

export default ComponentsModule;
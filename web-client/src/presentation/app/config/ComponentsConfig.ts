import ProfileButtonVM from "../../components/navigation-bar/profile-button/ProfileButtonVM";
import Bus from "../../../domain/utils/Bus";
import ProfileButtonAnonymousVM
    from "../../components/navigation-bar/profile-button-anonymous/ProfileButtonAnonymousVM";
import ProfileButtonAuthenticatedVM
    from "../../components/navigation-bar/profile-button-authenticated/ProfileButtonAuthenticatedVM";
import AppSession, {SessionState} from "../../../domain/auth/entities/AppSession";
import AppSessionAuthenticated from "../../../domain/auth/entities/AppSessionAuthenticated";
import value from "../../utils/binding/value";
import AppSessionAnonymous from "../../../domain/auth/entities/AppSessionAnonymous";
import AppSessionLoading from "../../../domain/auth/entities/AppSessionLoading";
import NavigationBarVM from "../../components/navigation-bar/NavigationBarVM";
import Logout from "../../../domain/auth/commands/Logout";
import OpenLoginDialog from "../../commands/OpenLoginDialog";
import OpenSignUpDialog from "../../commands/OpenSignUpDialog";
import Subject from "../../utils/binding/Subject";
import ProfileButtonLoadingVM from "../../components/navigation-bar/profile-button-loading/ProfileButtonLoadingVM";
import NavigationDelegate from "../NavigationDelegate.ts";
import mappedValue from "../../utils/binding/mappedValue.ts";

class ComponentsConfig {

    public navigation: NavigationDelegate | null = null;

    public constructor(
        private readonly appSession: AppSession,
        private readonly bus: Bus,
    ) {
    }

    public profileButton = (): Subject<ProfileButtonVM> => {

        const sessionSubject = this.appSession.state;
        const toButton = (session: SessionState): ProfileButtonVM => {

            switch (session.type) {
                case AppSessionAuthenticated.type:
                    return this.authenticatedProfileButton(session as AppSessionAuthenticated);

                case AppSessionAnonymous.type:
                    return this.anonymousProfileButton();

                case AppSessionLoading.type:
                    return this.loadingProfileButton();

                default:
                    throw new Error(`Unknown session state: ${session}`);
            }
        }

        const button = value(toButton(sessionSubject.value));
        sessionSubject.listen((session) => {
            button.set(toButton(session));
        });

        return button;
    };

    public navigationBar = () => {
        const showHostingButton = mappedValue(
            this.appSession.state,
            (session) => session.type === AppSessionAuthenticated.type
        );
        return new NavigationBarVM(
            this.profileButton(),
            showHostingButton
        )
    }

    private loadingProfileButton = () : ProfileButtonLoadingVM  => {
        return new ProfileButtonLoadingVM();
    }

    private anonymousProfileButton = (): ProfileButtonAnonymousVM => {

        return new ProfileButtonAnonymousVM(
            () => {
                this.bus.publish(new OpenLoginDialog());
            },
            () => {
                this.bus.publish(new OpenSignUpDialog());
            }
        );
    }

    private authenticatedProfileButton = (session: AppSessionAuthenticated): ProfileButtonAuthenticatedVM => {

        const userInfo = session.userInfo;
        const fullName = value(userInfo.value.fullName);
        const email = value(userInfo.value.email);
        const photo = value(userInfo.value.photo);

        const disposables = [
            fullName,
            photo,
            email
        ];

        const button = new ProfileButtonAuthenticatedVM(
            fullName,
            email,
            photo,
            () => {
                this.navigation?.openUserProfilePage();
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

export default ComponentsConfig;
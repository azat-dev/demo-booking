import AppViewModel, {
    ActiveDialogViewModel,
    ActiveDialogType,
} from "./AppViewModel";
import Subject from "../utils/binding/Subject";
import value from "../utils/binding/value";
import CurrentSessionStore from "../../domain/auth/CurrentSession/CurrentSessionStore";
import CurrentSessionStoreImpl from "../../domain/auth/CurrentSession/CurrentSessionStoreImpl";
import LocalStorageTokensRepository from "../../LocalStorageTokensRepository";
import AuthServiceImpl from "../../data/auth/services/AuthServiceImpl";
import LoginDialogViewModel from "../dialogs/login-dialog/LoginDialogViewModel";
import SessionStatus from "../../domain/auth/CurrentSession/Session/SessionStatus";
import SignUpDialogViewModel from "../dialogs/sign-up-dialog/SignUpDialogViewModel";

class AppViewModelImpl implements AppViewModel {
    public activeDialog: Subject<ActiveDialogViewModel | null>;
    private currentSession: CurrentSessionStore;

    public constructor() {
        this.activeDialog = value(null);

        const tokensRepository = new LocalStorageTokensRepository();
        const authService = new AuthServiceImpl();

        this.currentSession = new CurrentSessionStoreImpl(
            tokensRepository,
            authService
        );
    }

    private closeDialog = (): void => {
        this.activeDialog.set(null);
    };

    public openLoginDialog = (): void => {
        this.activeDialog.set({
            type: ActiveDialogType.Login,
            vm: new LoginDialogViewModel(
                async (email, password) => {
                    const session = this.currentSession.current.value;

                    if (session.type !== SessionStatus.ANONYMOUS) {
                        return;
                    }

                    await session.authenticate(email, password);
                },
                this.closeDialog,
                this.openSignUpDialog
            ),
        });
    };

    public openSignUpDialog = (): void => {
        this.activeDialog.set({
            type: ActiveDialogType.SignUp,
            vm: new SignUpDialogViewModel(
                async (email, password) => {
                    const session = this.currentSession.current.value;

                    if (session.type !== SessionStatus.ANONYMOUS) {
                        return;
                    }

                    await session.authenticate(email, password);
                },
                this.closeDialog,
                this.openLoginDialog
            ),
        });
    };

    public toggleFavorite = (id: string): void => {
        const currentUserSession = this.currentSession.current.value;
        if (currentUserSession.type !== SessionStatus.AUTHENTICATED) {
            this.openSignUpDialog();
            return;
        }

        throw new Error("Not implemented");
    };
}

export default AppViewModelImpl;

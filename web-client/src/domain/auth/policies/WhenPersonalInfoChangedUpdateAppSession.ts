import type AppSession from "../entities/AppSession";
import PersonalInfoDidChange from "../events/PersonalInfoDidChange";
import AppSessionAuthenticated from "../entities/AppSessionAuthenticated";
import Policy from "../../utils/Policy";


class WhenPersonalInfoChangedUpdateAppSession extends Policy {

    public static readonly TYPE = "WHEN_PERSONAL_INFO_CHANGED_UPDATE_APP_SESSION";

    public constructor(private appSession: AppSession) {
        super();
    }

    public execute = async (event: PersonalInfoDidChange): Promise<void> => {
        const currentState = this.appSession.state.value;
        const isAuthenticated = currentState instanceof AppSessionAuthenticated;

        if (!isAuthenticated) {
            return;
        }

        currentState.updateUserInfo(event.newPersonalInfo);
    }
}

export default WhenPersonalInfoChangedUpdateAppSession;
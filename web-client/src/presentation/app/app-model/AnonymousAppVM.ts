import AppVM, {DialogVM, InputAppVM} from "./AppVM";
import NavigationDelegate from "./NavigationDelegate";
import AccommodationId from "../../../domain/accommodations/AccommodationId";
import OpenLoginDialog from "../../commands/OpenLoginDialog";
import Bus from "../../../domain/utils/Bus";
import PagesModule from "../../../PagesModule";
import Subject from "../../utils/binding/Subject";
import Page from "../Page";
import value from "../../utils/binding/value";
import DialogsStore from "../../stores/DialogsStore";

class AnonymousAppVM implements InputAppVM {

    public static readonly TYPE = "ANONYMOUS_APP_VM";

    public get type() {
        return AnonymousAppVM.TYPE;
    }

    public navigationDelegate: NavigationDelegate | null = null;

    public constructor(
        private currentPage: Subject<Page | null>,
        private activeDialog: Subject<DialogVM | null>,
        private readonly pages: PagesModule,
        private readonly bus: Bus
    ) {

        activeDialog.set(null);
    }

    runProfilePage = async (): Promise<void> => {

        this.navigationDelegate?.navigateToMainPage(true);
        await this.openLoginDialog();
    }

    runMainPage = async (): Promise<void> => {
        this.currentPage.set(this.pages.mainPage());
    }

    runAccommodationDetailsPage(id: AccommodationId): Promise<void> {
        throw new Error("Method not implemented.");
    }

    public openLoginDialog = async (): Promise<void> => {
        this.bus.publish(new OpenLoginDialog());
    };
}

export default AnonymousAppVM;
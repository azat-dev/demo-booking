import PersonalUserInfo from "../../../domain/auth/values/PersonalUserInfo";
import UserPhotoVM from "./user-photo/UserPhotoVM";
import {ReadonlySubject} from "../../utils/binding/Subject";
import Disposables from "../../utils/binding/Disposables";
import KeepType from "../../../domain/utils/KeepType.ts";
import mappedValue from "../../utils/binding/mappedValue.ts";
import PersonalInfoItemVM from "./personal-info-item/PersonalInfoItemVM.ts";
import NavigationBarVM from "../../components/navigation-bar/NavigationBarVM.ts";

class PageUserProfileVM extends KeepType {

    public readonly name: PersonalInfoItemVM;
    public readonly email: PersonalInfoItemVM;
    private disposables = new Disposables();

    public constructor(
        public readonly navigationBar: NavigationBarVM,
        public readonly photo: UserPhotoVM,
        userInfo: ReadonlySubject<PersonalUserInfo>
    ) {

        super();

        const name = mappedValue(userInfo, v => v.fullName.toString());
        const email = mappedValue(userInfo, v => v.email.toString());

        this.disposables.addItems(
            name,
            email
        );


        this.name = new PersonalInfoItemVM(name);
        this.email = new PersonalInfoItemVM(email);
    }

    public dispose = () => {
        this.disposables.dispose();
    }
}

export default PageUserProfileVM;
import FullName from "../../../../../domain/auth/values/FullName";
import Subject, {ReadonlySubject} from "../../../../utils/binding/Subject";
import value from "../../../../utils/binding/value";
import {PhotoPath} from "../../../../../domain/auth/values/PhotoPath";
import {Cancellable} from "../../../../utils/binding/Cancellable";

class AvatarButtonViewModel {
    public fullName: Subject<string>;
    public shortName: Subject<string>;
    public photoUrl: Subject<string | null>;

    private disposables: Cancellable[] = [];

    public constructor(fullName: ReadonlySubject<FullName>, photo: ReadonlySubject<PhotoPath | null>) {

        this.fullName = value(fullName.value.toString());
        this.shortName = value(fullName.value.getInitials());
        this.photoUrl = value(photo.value?.url ?? null);

        this.disposables = [
            fullName.listen((fullName) => {
                this.fullName.set(fullName.toString());
                this.shortName.set(fullName.getInitials());
            }),

            photo.listen((photo) => {
                this.photoUrl.set(photo?.url ?? null);
            })
        ];
    }

    public dispose = () => {
        this.disposables.forEach((d) => d.cancel());
    }
}

export default AvatarButtonViewModel;

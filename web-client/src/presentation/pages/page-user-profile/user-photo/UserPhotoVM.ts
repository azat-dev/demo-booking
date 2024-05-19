import Subject from "../../../utils/binding/Subject";
import value from "../../../utils/binding/value";
import { fileDialog } from "file-select-dialog";
import fullName from "../../../../domain/auth/CurrentSession/Session/FullName";
import FullName from "../../../../domain/auth/CurrentSession/Session/FullName";
import {PhotoPath} from "../../../../domain/auth/values/PhotoPath";

class UserPhotoVM {

    public readonly initials: Subject<string>;
    public readonly photo: Subject<string | undefined>;
    public isUploading: Subject<boolean>;

    public constructor(
        fullName: FullName,
        photo: PhotoPath | null,
        private uploadPhoto: (file: File) => Promise<void>
    ) {

        this.isUploading = value(false);
        this.photo = value(photo?.url ?? undefined);
        this.initials = value(fullName.getInitials());
    }

    public updatePhoto = (newPhoto: PhotoPath | null) => {
        this.photo.set(newPhoto?.url ?? undefined);
        this.isUploading.set(false);
    }

    public updateFullName = (newFullName: FullName) => {
        this.initials.set(newFullName.getInitials());
    }

    public openUploadDialog = async () => {

        const files = await fileDialog({ accept: ['.png', '.jpg', '.webp', '.jpeg'] });
        if (files.length === 0) {
            return;
        }

        let file = files[0];

        this.isUploading.set(true);
        try {
            await this.uploadPhoto(file);
        } catch (error) {
            console.error(error);
        }
        this.isUploading.set(false);
    }
}

export default UserPhotoVM;
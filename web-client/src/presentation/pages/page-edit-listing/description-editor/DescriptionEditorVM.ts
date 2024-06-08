import Subject from "../../../utils/binding/Subject.ts";
import value from "../../../utils/binding/value.ts";

class DescriptionEditorVM {

    public numberOfCharacters: Subject<number>;
    public maxNumberOfCharacters: number = 500;
    public description: Subject<string>;

    public canMoveNext: Subject<boolean>;

    public constructor(
        initialValue: string
    ) {
        this.description = value(initialValue ?? '');
        this.numberOfCharacters = value(initialValue?.length ?? 0);
        this.canMoveNext = value(false);
        this.updateCanMoveNext();
    }

    public onChange = (e: any) => {
        const newTitle = e.target.value;
        this.description.set(newTitle);
        this.numberOfCharacters.set(newTitle.length);
        this.updateCanMoveNext();
    }

    private updateCanMoveNext = () => {
        debugger
        this.canMoveNext.set(this.numberOfCharacters.value > 0 && this.numberOfCharacters.value <= this.maxNumberOfCharacters);

    }
}

export default DescriptionEditorVM;
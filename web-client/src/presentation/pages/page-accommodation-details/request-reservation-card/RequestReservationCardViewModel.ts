import dayjs from "dayjs";
import DateRangePickerViewModel from "../../../components/date-picker/date-range-picker/DateRangePickerViewModel";
import {
    AvailableDates,
    CalendarRange,
} from "../../../components/date-picker/month-view/MonthViewModel";
import Subject from "../../../utils/binding/Subject";
import value from "../../../utils/binding/value";

export enum CostDetailsStatus {
    LOADING = "loading",
    LOADED = "loaded",
    NOT_AVAILABLE = "not-available",
}

export type CostDetails =
    | {
          status: CostDetailsStatus.LOADING;
      }
    | {
          status: CostDetailsStatus.LOADED;
          totalCost: string;
          accommodationCost: string;
          serviceFee: string;
      }
    | {
          status: CostDetailsStatus.NOT_AVAILABLE;
      };

class TestAvailableDates extends AvailableDates {
    isAvailable = (date: Date): boolean => {
        return dayjs(date).isAfter(new Date());
    };
}

class RequestReservationCardViewModel {
    public readonly costDetails: Subject<CostDetails>;
    public readonly dateRangePicker: DateRangePickerViewModel;
    private currentDateRange: CalendarRange | undefined;

    public constructor() {
        this.dateRangePicker = new DateRangePickerViewModel(
            this.currentDateRange,
            new TestAvailableDates(),
            this.didChangeDates
        );
        this.costDetails = value({ status: CostDetailsStatus.NOT_AVAILABLE });
        this.costDetails.set({
            status: CostDetailsStatus.LOADED,
            totalCost: "$100",
            accommodationCost: "$80",
            serviceFee: "$20",
        });
    }

    private didChangeDates = (newRange: CalendarRange) => {
        this.currentDateRange = newRange;
        this.dateRangePicker.updateRange(newRange);
    };

    public requestReservation = (): void => {
        this.costDetails.set({ status: CostDetailsStatus.LOADING });

        setTimeout(() => {
            this.costDetails.set({
                status: CostDetailsStatus.LOADED,
                totalCost: "$100",
                accommodationCost: "$80",
                serviceFee: "$20",
            });
        }, 2000);
    };
}

export default RequestReservationCardViewModel;

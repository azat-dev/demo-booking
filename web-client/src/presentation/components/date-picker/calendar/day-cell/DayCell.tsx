import React from "react";

import PropsDayCell from "./props";
import style from "./style.module.scss";
import Box from "@mui/joy/Box";
import { AspectRatio, Button, Typography } from "@mui/joy";
import useUpdatesFrom from "../../../../utils/binding/useUpdatesFrom";
import { SelectionState } from "./DayCellViewModel";

const DayCell = ({ vm }: PropsDayCell) => {
    const [isDisabled, selectionState] = useUpdatesFrom(
        vm.isDisabled,
        vm.selectionState
    );

    let titleColor = "inherit";
    if (selectionState === SelectionState.Single) {
        titleColor = "white";
    }

    let circleColor = "transparent";
    let backgroundColor = "background.paper";
    let showBackground = false;
    let backgroundStart: any = 0;
    let backgroundEnd: any = 0;
    let backgroundWidth = "100%";

    switch (selectionState) {
        case SelectionState.Single:
            circleColor = "primary";
            backgroundColor = "background.level3";
            showBackground = false;
            break;
        case SelectionState.End:
            backgroundColor = "background.level3";
            circleColor = "primary";
            showBackground = true;
            backgroundStart = 0;
            backgroundEnd = "unset";
            backgroundWidth = "50%";
            break;
        case SelectionState.Start:
            backgroundColor = "background.level3";
            circleColor = "primary";
            backgroundWidth = "50%";
            backgroundStart = "unset";
            showBackground = true;
            backgroundEnd = 0;
            break;
        case SelectionState.Middle:
            circleColor = "transparent";
            backgroundColor = "background.level3";
            showBackground = true;
            break;
    }

    return (
        <AspectRatio ratio="1/1" variant="plain">
            <div className={style.dayCell} data-selection={selectionState}>
                <div
                    className={style.circle}
                    onClick={vm.click}
                    data-disabled={!!isDisabled}
                    data-selected={[
                        SelectionState.Single,
                        SelectionState.Start,
                        SelectionState.End,
                    ].includes(selectionState)}
                >
                    <Typography
                        fontSize="sm"
                        fontWeight="md"
                        sx={{ color: "inherit" }}
                    >
                        {vm.title}
                    </Typography>
                </div>
            </div>
        </AspectRatio>
    );
};

export default React.memo(DayCell);

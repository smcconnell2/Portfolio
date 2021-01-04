$(document).ready(start);


function start(){

    //$("#subBody").click(subClicked);
    $("#page-body").click(calBodyClicked);
    $("#calendarButton").click(calButtonClicked);
    $("#exitPosition").click(exitCalendarClicked);
}

/*
function subClicked(){
    console.log("subBody was clicked");
}*/

function calBodyClicked(){
    console.log("Calendar body was clicked");
}

function calButtonClicked(){
    console.log("Button was clicked");
    
    $("#cal-body").css("display", "inline-block");

    $("#subBody").css("position", "relative");
    $("#subBody").css("z-index", "0");
}

function exitCalendarClicked(){
    console.log("Exit was clicked.");

    $("#cal-body").css("display", "none");
}

// This function reads the ID value from the image that has been clicked.
$(document).ready(function () {
    $("#rock").click(function () {
        compare("rock");
    });
    $("#paper").click(function () {
        compare("paper");
    });
    $("#scissors").click(function () {
        compare("scissors");
    });
});

//Rock–paper–scissors (also known as scissors–rock–paper or other variants) is a hand game usually played between two people, in which each player simultaneously forms one of three shapes with an outstretched hand. These shapes are "rock" (a closed fist), "paper" (a flat hand), and "scissors" (a fist with the index finger and middle finger extended, forming a V). "Scissors" is identical to the two-fingered V sign (also indicating "victory" or "peace") except that it is pointed horizontally instead of being held upright in the air. A simultaneous, zero-sum game, it has only two possible outcomes: a draw, or a win for one player and a loss for the other.
// This function checks who is the winner
function compare(choice1) {
    // Computer's choice will be stored as systemChoice which is randomly generated
    var systemChoice = Math.random();
    if (systemChoice < 0.34) {
        choice2 = "rock";
    } else if (systemChoice <= 0.67) {
        choice2 = "paper";
    } else {
        choice2 = "scissors";
    }
    // Checks if user's choice and Systems choice are same
    if (choice1 === choice2) {

        alert( "Its a tie!!!" + " " + "Play it again.");
    }

    // Check if user's choice is rock
    else if (choice1 === "rock") {
        // Check if system's choice is scissors when User's choice is rock
        if (choice2 === "scissors") {
            alert( "rock wins"+ "!!! " + "Nice job! You won!");
        }
        // Check if system's choice is Paper when User's choice is rock
        else {
            alert("paper wins"  + "!!! " + "Computer Wins, Better luck Next time!!!.") ;
        }
    }
    // Check if user's choice is Paper
    else if (choice1 === "paper") {
        // Check if system's choice is rock when User's choice is paper
        if (choice2 === "rock") {
            alert("paper wins"+ "!!! " + "Nice job! You won!") ;
        }
        // Check if system's choice is Scissors when User's choice is paper
        else {
            alert("scissors wins"+ "!!! " + "Computer Wins, Better luck Next time!!!.") ;
        }

    }
    // Check if user's choice is scissors
    else if (choice1 === "scissors") {
        // Check if system's choice is rock when User's choice is Scissors
        if (choice2 === "rock") {
            alert("rock wins"  + " " + "Computer Wins, Better luck Next time!!!.") ;
        }
        // Check if system's choice is paper when User's choice is Scissors
        else {
            alert("scissors win"  + " " + "Nice job! You won!") ;
        }
    }


}
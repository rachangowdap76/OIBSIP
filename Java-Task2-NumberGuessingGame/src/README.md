# Number Guessing Game (Java Console)

A console game where the computer picks a random number and the player guesses it, getting "Too High!" or "Too Low!" hints until they win or run out of attempts.

## Features
- Random number generated at the start of every round (`java.util.Random`)
- Three responses after each guess: **Too High!**, **Too Low!**, **Correct!**
- Attempt counter shown on every prompt (e.g. `Attempt 3/7`)
- Maximum attempts limit; on a loss, prints **You Lost!** and reveals the number
- **Play Again** (yes/no) after each round
- Round-by-round score summary, e.g. `Round 2 - guessed in 5 attempts`
- Difficulty levels (bonus):

| Level  | Range  | Attempts |
|--------|--------|----------|
| Easy   | 1-50   | 10       |
| Medium | 1-100  | 7        |
| Hard   | 1-200  | 5        |

- Input validation: letters and out-of-range numbers are rejected without crashing

## Tech Stack
- Java (console application)
- Concepts used: `Random`, `Scanner`, `while` loops, `if-else`, `switch`, `ArrayList`, `try-catch`

## Requirements
- JDK 17 or newer (tested with JDK 24)

## How to Run

```bash
javac NumberGuessingGame.java
java NumberGuessingGame
```

Or open the folder in IntelliJ IDEA and click **Run**.

## Sample Output

```
=== NUMBER GUESSING GAME ===

Choose difficulty:
1. Easy   (1-50,  10 attempts)
2. Medium (1-100,  7 attempts)
3. Hard   (1-200,  5 attempts)
Your choice: 2

--- Round 1 ---
I'm thinking of a number between 1 and 100. You have 7 attempts.
Attempt 1/7 - Enter your guess: 50
Too High!
Attempt 2/7 - Enter your guess: 25
Too Low!
Attempt 3/7 - Enter your guess: 37
Correct!
You guessed it in 3 attempt(s)!

Play again? (yes/no): no

=== GAME SUMMARY ===
Round 1 - guessed in 3 attempts
Rounds played: 1 | Won: 1 | Lost: 0
Thanks for playing!
```

## Project Structure

```
NumberGuessingGame/
├── NumberGuessingGame.java
└── README.md
```

## Author
Rachan

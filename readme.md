
Tower of Hanoi Protocol (TOHP)
A basic console game protocol that allows the user to play a traditional game of Tower of Hanoi
with at least 3 disks (theoretically more).

| Client Request | Server Response                                      | Description                             |
|----------------|------------------------------------------------------|-----------------------------------------|
| START n        | n and confirmation of a new game                     | Start a new game with n number of disks |
| MOVE a b       | confirmation of valid or invalid move and the result | Move a top disk from peg a to peg b     |
| DISPLAY        | Console representation of game                       | Display the current game in the console |
| QUIT           | Quit the connection                                  |                                         |

Source: Cay Horstmann, Big Java
trivia.db Tables and Schemas
Please update if questions are added to the database

---------------------------------------------------
TRUE_FALSE
question_number: INT PRIMARY KEY AUTOINCREMENT
question: TEXT
answer: TEXT (should only be 'T' or 'F')

Current list of entries:
1, This is the default T/F question, T
2, EWU stands for Eastern Washington University, T
3, 1 + 2 = 4, F


---------------------------------------------------
MULTIPLE_CHOICE
question_number: INT PRIMARY KEY AUTOINCREMENT
question: TEXT
answer: TEXT
response1: TEXT ("Response*" is an incorrect choice)
response2: TEXT
response3: TEXT

Current list of entries:
1, This is the default multiple choice question, This is the answer, WRONG RESPONSE1, WRONG RESPONSE2, WRONG RESPONSE3


---------------------------------------------------
SHORT_ANSWER
question_number: INT PRIMARY KEY AUTOINCREMENT
question: TEXT
answer: TEXT

Current list of entries:
1, This is the default short answer question, This is the answer

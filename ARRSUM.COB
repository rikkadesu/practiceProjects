       IDENTIFICATION DIVISION.
       PROGRAM-ID. ARRAY-SUM.
       ENVIRONMENT DIVISION.
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01  ARR1.
           03 ARRAY1 PIC 9(3) OCCURS 5 TIMES.
           03 I PIC 9 VALUE 1.
       01  ARR2.
           03 ARRAY2 PIC 9(3) OCCURS 5 TIMES.
       01  ARR3.
           03 ARRAY3 PIC 9(4) OCCURS 5 TIMES.
       01  SPC PIC X(4) VALUE SPACES.
       SCREEN SECTION.
       01  DEL-SCRN.
           03 BLANK SCREEN.
       PROCEDURE DIVISION.
       MAIN-PROC.
           DISPLAY DEL-SCRN.
           PERFORM USR-IN1 VARYING I FROM 1 BY 1 UNTIL I > 5.
           DISPLAY DEL-SCRN.
           PERFORM USR-IN2 VARYING I FROM 1 BY 1 UNTIL I > 5.
           PERFORM CMPT-RTN1 VARYING I FROM 1 BY 1 UNTIL I > 5.
           DISPLAY DEL-SCRN.
           PERFORM DISP-RTN1.

       USR-IN1.
           DISPLAY "Enter a number on first array: "
           WITH NO ADVANCING.
           ACCEPT ARRAY1(I).

       USR-IN2.
           DISPLAY "Enter a number on second array: "
           WITH NO ADVANCING.
           ACCEPT ARRAY2(I).

       CMPT-RTN1.
           COMPUTE ARRAY3(I) = ARRAY1(I) + ARRAY2(I).

       DISP-RTN1.
           DISPLAY "Elements in the first array are:" LINE 3 COLUMN 5.
           DISPLAY SPC.
           PERFORM DISP-ARR1 VARYING I FROM 1 BY 1 UNTIL I > 5.
           DISPLAY SPC.
           DISPLAY "Elements in the second array are:" LINE 6 COLUMN 5.
           DISPLAY SPC.
           PERFORM DISP-ARR2 VARYING I FROM 1 BY 1 UNTIL I > 5.
           DISPLAY SPC.
           DISPLAY "The sum of the two arrays is:" LINE 9 COLUMN 5.
           DISPLAY SPC.
           PERFORM DISP-RTN2 VARYING I FROM 1 BY 1 UNTIL I > 5.
           PERFORM CLOSE-RTN.

       DISP-ARR1.
           DISPLAY SPC ARRAY1(I) WITH NO ADVANCING.

       DISP-ARR2.
           DISPLAY SPC ARRAY2(I) WITH NO ADVANCING.

       DISP-RTN2.
           DISPLAY SPC ARRAY3(I) WITH NO ADVANCING.
                     
       CLOSE-RTN.
           STOP RUN.

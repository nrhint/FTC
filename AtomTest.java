package FTCFiles;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

//@TeleOp

@Autonomous(name="Auto", group="Pushbot")

public class AtomTest extends LinearOpMode {

    /* Declare OpMode members. */
    PB         robot   = new PB();   // Use a Pushbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                                                      (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        robot.FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //robot.leftDrive3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //robot.rightDrive3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //robot.leftDrive3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //robot.rightDrive3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                          robot.FrontLeft.getCurrentPosition(),
                          robot.FrontRight.getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
/*
Here are importiant vars:
deg to in = 7.5
48 inches is 360 deg turn.
*/
        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        
        //Drive in square
        double turn = 8.3;
        int distance = 24;
        for (int i = 0; i<4; i ++){
            encoderDrive(DRIVE_SPEED,  distance,  distance, 10);
            encoderDrive(TURN_SPEED, 5*turn, 5*-turn, 10);
        }
        //encoderDrive(DRIVE_SPEED,  48,  48, 3);  // S1: Forward 48 Inches with 5 Sec timeout
        //encoderDrive(TURN_SPEED,   12, -12, 4.0);  // S2: Turn Right 12 Inches with 4 Sec timeout
        //encoderDrive(DRIVE_SPEED, -48, -48, 3);  // S3: Reverse 24 Inches with 4 Sec timeout

//        robot.leftClaw.setPosition(1.0);            // S4: Stop and close the claw.
//        robot.rightClaw.setPosition(0.0);
//        sleep(1000);     // pause for servos to move

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }

    /*
     *  Method to perfmorm a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.FrontLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newLeftTarget = robot.RearLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            //newLeftTarget = robot.leftDrive3.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.FrontRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newRightTarget = robot.RearRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            //newRightTarget = robot.rightDrive3.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            robot.FrontLeft.setTargetPosition(newLeftTarget);
            robot.RearLeft.setTargetPosition(newLeftTarget);
            //robot.leftDrive3.setTargetPosition(newLeftTarget);
            robot.FrontRight.setTargetPosition(newRightTarget);
            robot.RearRight.setTargetPosition(newRightTarget);
            //robot.rightDrive3.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            robot.FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //robot.leftDrive3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //robot.rightDrive3.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.FrontLeft.setPower(Math.abs(speed));
            robot.RearLeft.setPower(Math.abs(speed));
            //robot.leftDrive3.setPower(Math.abs(speed));
            robot.FrontRight.setPower(Math.abs(speed));
            robot.RearRight.setPower(Math.abs(speed));
            //robot.rightDrive3.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                   (runtime.seconds() < timeoutS) &&
                   (robot.FrontLeft.isBusy() && robot.FrontRight.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                                            robot.FrontLeft.getCurrentPosition(),
                                            robot.FrontRight.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.FrontLeft.setPower(0);
            robot.RearLeft.setPower(0);
            //robot.leftDrive3.setPower(0);
            robot.FrontRight.setPower(0);
            robot.RearRight.setPower(0);
            //robot.rightDrive3.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //robot.leftDrive3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //robot.rightDrive3.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

              //sleep(1000);   // optional pause after each move
        }
    }
}

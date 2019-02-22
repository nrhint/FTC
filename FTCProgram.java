/*
Copyright 2018 FIRST Tech Challenge Team 14352

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package FTCFiles;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Remove a @Disabled the on the next line or two (if present) to add this opmode to the Driver Station OpMode list,
 * or add a @Disabled annotation to prevent this OpMode from being added to the Driver Station
 */
@TeleOp

public class FTCProgram extends LinearOpMode {

    //Vars
    //Motors:
    private DcMotor FrontRight = null;
    private DcMotor FrontLeft = null;
    private DcMotor MidRight = null;
    private DcMotor MidLeft = null;
    private DcMotor BackRight = null;
    private DcMotor BackLeft = null;
    private DcMotor Arm = null;
    //Servos:
    private Servo Hand = null;

    //Speed vars for the motors
    double FrontLeftSpeed = 0;
    double FrontRightSpeed = 0;
    double MidLeftSpeed = 0;
    double MidRightSpeed = 0;
    double BackLeftSpeed = 0;
    double BackRightSpeed = 0;
    double ArmSpeed = 0;
    double HandPos = 0;
    //Vars for speed controlls
    double RightTrigger = 0;
    double LeftTrigger = 0;
    
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        /*
        FrontRight = hardwareMap.get(DcMotor.class, "m0");
        FrontRight.setDirection(DcMotor.Direction.FORWARD);
        FrontLeft = hardwareMap.get(DcMotor.class, "m1");
        FrontLeft.setDirection(DcMotor.Direction.REVERSE);
        BackRight = hardwareMap.get(DcMotor.class, "m2");
        BackRight.setDirection(DcMotor.Direction.FORWARD);
        BackLeft = hardwareMap.get(DcMotor.class, "m3");
        BackLeft.setDirection(DcMotor.Direction.REVERSE);
        Arm = hardwareMap.get(DcMotor.class, "m4");
        Arm.setDirection(DcMotor.Direction.FORWARD);
        MidRight = hardwareMap.get(DcMotor.class, "m6");
        MidRight.setDirection(DcMotor.Direction.FORWARD);
        MidLeft = hardwareMap.get(DcMotor.class, "m5");
        MidLeft.setDirection(DcMotor.Direction.REVERSE);
        */
        BackLeft = hardwareMap.get(DcMotor.class, "m0");
        MidLeft = hardwareMap.get(DcMotor.class, "m1");
        FrontLeft = hardwareMap.get(DcMotor.class, "m2");
        BackRight = hardwareMap.get(DcMotor.class, "m3");
        MidRight = hardwareMap.get(DcMotor.class, "m4");
        FrontRight = hardwareMap.get(DcMotor.class, "m5");
        Arm = hardwareMap.get(DcMotor.class, "m6");
        
        FrontRight.setDirection(DcMotor.Direction.REVERSE);
        MidRight.setDirection(DcMotor.Direction.REVERSE);
        BackRight.setDirection(DcMotor.Direction.REVERSE);
        
        
        //Setup Servos
        Hand = hardwareMap.get(Servo.class, "s0");
        // Wait for the game to start (driver presses PLAY)
        //Attonimus  code:?
        
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //Main loop:
            //get the buper stat
            LeftTrigger = gamepad1.left_trigger+1;
            RightTrigger = gamepad1.right_trigger+1;
            FrontLeftSpeed = gamepad1.right_stick_y/4*RightTrigger*LeftTrigger;
            MidLeftSpeed = gamepad1.right_stick_y/4*RightTrigger*LeftTrigger;
            BackLeftSpeed = gamepad1.right_stick_y/4*RightTrigger*LeftTrigger;
            FrontRightSpeed = gamepad1.left_stick_y/4*RightTrigger*LeftTrigger;
            MidRightSpeed = gamepad1.left_stick_y/4*RightTrigger*LeftTrigger;
            BackRightSpeed = gamepad1.left_stick_y/4*RightTrigger*LeftTrigger;
            ArmSpeed = gamepad2.right_stick_y;
            HandPos += gamepad2.left_stick_y/100*-1;
            //Bind HandPos between 0 and 1:
            if (HandPos > 1){HandPos = 1;}
            else if (HandPos < 0){HandPos=0;}
            
            //Write the speeds to the motors.
            FrontLeft.setPower(FrontLeftSpeed);
            FrontRight.setPower(FrontRightSpeed);
            MidLeft.setPower(MidLeftSpeed);
            MidRight.setPower(MidRightSpeed);
            BackLeft.setPower(BackLeftSpeed);
            BackRight.setPower(BackRightSpeed);
            Arm.setPower(ArmSpeed);
            Hand.setPosition(HandPos);
            
            
            
            telemetry.addData("Status", "Running");
            telemetry.addData("HandPos = ", HandPos);
            telemetry.update();

        }
    }
}

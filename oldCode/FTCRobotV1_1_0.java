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

//Version Info:
//(for some reason I cant do multi-line comments...)

//V1:
//Basic drive back and forth.

//V1_1_0:
//Adding speed controls. They are level 1 speed controlls now.
//plan on nudge(fine) controls in version 1_2.


package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;
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

public class FTCRobotV1_1_0 extends LinearOpMode {
    
    //Set the motors to null so when I get the hardware map it wont get an error
    //DC Motors
    private DcMotor m0 = null;
    private DcMotor m1 = null;
    private DcMotor m2 = null;
    private DcMotor m3 = null;
    //Servos (Notusing in V1_0 to V1_1)
    private Servo s0 = null;
    private Servo s1 = null;
    private Servo s2 = null;
    private Servo s3 = null;


    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initalizing...");
        telemetry.update();
        m0 = hardwareMap.get(DcMotor.class, "m0");
        m1 = hardwareMap.get(DcMotor.class, "m1");
        m2 = hardwareMap.get(DcMotor.class, "m2");
        m3 = hardwareMap.get(DcMotor.class, "m3");
        
        //Set the dirrection of the DcMotors
        //M0, M2 are left and M1, M3 are right.
        m0.setDirection(DcMotor.Direction.FORWARD);
        m1.setDirection(DcMotor.Direction.REVERSE);
        m2.setDirection(DcMotor.Direction.FORWARD);
        m3.setDirection(DcMotor.Direction.REVERSE);
        
        //Setup vars for telemetry and motor power:
        double m0Power = 0;
        double m1Power = 0;
        double m2Power = 0;
        double m3Power = 0;

        telemetry.addData("Status", "Press PLAY!");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            
            //Setup bmpers for speed control.
            //double Pad1RButt = Pad1RButtB ? 2 : 1;(cast Bool to INT)
            double leftTrigger = gamepad1.left_trigger+1;//add one so that it will mutupuly.
            double rightTrigger = gamepad1.right_trigger+1;//add one so that it will mutupuly.

            //Do the math!
            m0Power = (gamepad1.left_stick_y/2*leftTrigger);
            m1Power = (gamepad1.right_stick_y/2*rightTrigger);
            m2Power = (gamepad1.left_stick_y/2*leftTrigger);
            m3Power = (gamepad1.right_stick_y/2*rightTrigger);
            
            //Send the data to the motors:
            m0.setPower(m0Power);
            m1.setPower(m1Power);
            m2.setPower(m2Power);
            m3.setPower(m3Power);
            
            //all the telementry:
            telemetry.addData("Status", "Running");
            //telemetry.addData("CurrMotorSpeeds: m0:(%.2f), m1:(%.2f), m2:(%.2f), m3:(%.2f)", m0Power, m1Power, m2Power, m3Power);
            telemetry.update();

        }
    }
}

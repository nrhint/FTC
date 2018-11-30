/*  Nathan Hinton FTC 2018-2019

Revision history:  All revisions should be loaded to the computer.

V1.0:
All the controlls are working for the bot.  You have the drive working but the only problem 
is the turning is not the expected way.  The arm workd and the claw workds as well.  I want
to add a slower mode to the robot so that it is easier to drive with percision.

V1.1:
Fixed the turning.

V1.2:
I changed the sticks and then added the slower controlls.  Pushing the right trigger or the right stick will mak the robots
total speed decrease by 50%.  This is a EitherOr thing.

*/


/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.Test0;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")

public class BasicLin extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor armLift = null;//Set the arm
    private Servo   LClaw = null;//Set the left Claw Servo
    private Servo   RClaw = null;//Set the right claw servo

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        armLift    = hardwareMap.get(DcMotor.class, "armLift");
        LClaw      = hardwareMap.get(Servo.class, "LClaw");
        RClaw      = hardwareMap.get(Servo.class, "RClaw");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        LClaw.setDirection(Servo.Direction.FORWARD);
        RClaw.setDirection(Servo.Direction.REVERSE);
        

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            double elbowPower;
            double clawPower;
            double clawPos;

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            //I AM COMMENTING OUT THE WORKING TO MAKE THE TANK RUN.
            /*double drive = -gamepad1.right_stick_y;
            double turn  =  gamepad1.right_stick_x;
            double Pad1LStickY = gamepad1.left_stick_y;
            double Pad1LStickX = gamepad1.left_stick_x;
            Boolean Pad1RStickBut = gamepad1.right_stick_button;//Get the stat of the button
            double Pad1RTrig = gamepad1.right_trigger;//Get the stat of the button
            double Pad1RStickButInt = Pad1RStickBut ? 1 : 0;
            leftPower    = Range.clip(((drive + turn)/(Pad1RStickButInt+1))/(Pad1RTrig+1), -1.0, 1.0) ;
            rightPower   = Range.clip(((drive - turn)/(Pad1RStickButInt+1))/(Pad1RTrig+1), -1.0, 1.0) ;
            elbowPower   = Range.clip((Pad1LStickY/-2.5), -1.0, 1.0);
            clawPower    = Range.clip((Pad1LStickX/100), -1.0, 1.0);*/
            double RDrive = gamepad1.right_stick_y/-4;
            double LDrive = gamepad1.left_stick_y/-4;
            double Pad1RTrig = 1+gamepad1.right_trigger;//Get the stat of the button
            double Pad1LTrig = 1+gamepad1.left_trigger;//Get the stat of the button
            Boolean Pad1RButtB = gamepad1.right_bumper;//Get the stat of the button
            Boolean Pad1LButtB = gamepad1.left_bumper;//Get the stat of the button
            double Pad1RButt = Pad1RButtB ? 2 : 1;
            double Pad1LButt = Pad1LButtB ? 2 : 1;
            rightPower      = Range.clip(RDrive*Pad1RTrig*Pad1RButt, -1.0, 1.0);
            leftPower       = Range.clip(LDrive*Pad1LTrig*Pad1LButt, -1.0, 1.0);
            //Here are the arm controlls
            
            elbowPower      = -(gamepad2.left_stick_y/4)*(gamepad2.left_trigger+1)*(gamepad2.right_trigger+1);
            if (gamepad2.right_bumper == true) {
                clawPower = gamepad2.right_stick_x;
            } else {
                clawPower       = gamepad2.right_stick_x/250;
            }
            //Add the buttons for fine positioning.
            
            if(gamepad1.y) {
                //move motor for .25Sec
                double StartTime = runtime.time();
                rightPower  = .16;
                leftPower   = .16;
                if (StartTime - runtime.time() > .25) {
                    rightPower  = .0;
                    leftPower   = .0;
                }
            } else if(gamepad1.a) {
                //move motor for .25Sec
                double StartTime = runtime.time();
                rightPower  = -.16;
                leftPower   = -.16;
                if (StartTime - runtime.time() > .25) {
                    rightPower  = .0;
                    leftPower   = .0;
                }
            } else if(gamepad1.b) {
                //move motor for .25Sec
                double StartTime = runtime.time();
                rightPower  = -.15;
                leftPower   = .15;
                if (StartTime - runtime.time() > .25) {
                    rightPower  = .0;
                    leftPower   = .0;
                }
            } else if(gamepad1.x) {
                //move motor for .25Sec
                double StartTime = runtime.time();
                rightPower  = .15;
                leftPower   = -.15;
                if (StartTime - runtime.time() > .25) {
                    rightPower  = .0;
                    leftPower   = .0;
                }
            }
            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;

            // Send calculated power to wheels
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
            armLift.setPower(elbowPower);
            clawPos = LClaw.getPosition();
            LClaw.setPosition(clawPos+clawPower);
            RClaw.setPosition(clawPos+clawPower);
            
            

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f), arm (%.2f)", leftPower, rightPower, elbowPower);
            telemetry.addData("Servos", "LClaw (%.2f), RClaw (%.2f), LClawPos (%.2f)", clawPower, clawPower, LClaw.getPosition());

            telemetry.update();
        }
    }
}

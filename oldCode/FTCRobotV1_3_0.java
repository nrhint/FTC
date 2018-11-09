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

/*
//Version Info:

//V1:
//Basic drive back and forth.

//V1_1_0:
//Adding speed controls. They are level 1 speed controlls now.

//V1_1_1:
//Adding the level 2 speed controls...

//V1_2:
//FINALY! Doing the nudge controlls.  I think that there is a better way than I was 
//doing it last time.  Last time I was timing it to .25 seconds I think that this 
//time I wont do that.  I also will use if, elif and else not if, if...  I am 
//excited to be adding this.  We still need to get more large motors and the arm 
//isnt ready so V2_0 wont be made until then(I might make a trial and then have to 
//wait...). (Almost got it perfect the first time, the controlls were backwards...)

//V1_3_0:
I fixed the multi line comments.  We have the other large motors and I am adding 
them into the program.  I dont know what they will be used for but I will add in the
vars and stuff for use so that when we are ready they will be there.  I also need to
start doing stuff with the attonomus mode and add some of that into the program.
*/

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class FTCRobotV1_3_0 extends LinearOpMode {
    
    //Set the motors to null so when I get the hardware map it wont get an error
    //DC Motors
    private DcMotor m0 = null;
    private DcMotor m1 = null;
    private DcMotor m2 = null;
    private DcMotor m3 = null;
    private DcMotor m4 = null;
    private DcMotor m5 = null;
    private DcMotor m6 = null;
    private DcMotor m7 = null;
    //Servos (Notusing in V1_0 to V1_1)
    private Servo s0 = null;
    private Servo s1 = null;
    private Servo s2 = null;
    private Servo s3 = null;
    private Servo s4 = null;
    private Servo s5 = null;
    private Servo s6 = null;
    private Servo s7 = null;
    private Servo s8 = null;


    @Override
    public void runOpMode() {

        telemetry.addData("Status", "Initalizing...");
        telemetry.update();
        m0 = hardwareMap.get(DcMotor.class, "m0");
        m1 = hardwareMap.get(DcMotor.class, "m1");
        m2 = hardwareMap.get(DcMotor.class, "m2");
        m3 = hardwareMap.get(DcMotor.class, "m3");
        m4 = hardwareMap.get(DcMotor.class, "m4");
        m5 = hardwareMap.get(DcMotor.class, "m5");
        m6 = hardwareMap.get(DcMotor.class, "m6");
        m7 = hardwareMap.get(DcMotor.class, "m7");
        
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
        double m4Power = 0;
        double m5Power = 0;
        double m6Power = 0;
        double m7Power = 0;


        telemetry.addData("Status", "Press PLAY!");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            
            //Setup bmpers for speed control.
            //double Pad1RButt = Pad1RButtB ? 2 : 1;(cast Bool to INT)
            //Level 1 speed controls:
            double leftTrigger = gamepad1.left_trigger+1;//add one so that it will mutupuly.
            double rightTrigger = gamepad1.right_trigger+1;//add one so that it will mutupuly.
            //level2 speed controls:
            boolean leftBumperBool = gamepad1.left_bumper;
            boolean rightBumperBool = gamepad1.right_bumper;
            //(Cast the bool as an int)
            double leftBumper = leftBumperBool ? 2 : 1;//I dod the 2, 1 where it would usauly be 0:1 because I need the value 2 to make the number bogger so I skipped adding it with this.
            double rightBumper = rightBumperBool ? 2 : 1;//Same as above...

            //Do the math!
            m0Power = (gamepad1.left_stick_y/4*leftTrigger*leftBumper);
            m1Power = (gamepad1.right_stick_y/4*rightTrigger*rightBumper);
            m2Power = (gamepad1.left_stick_y/4*leftTrigger*leftBumper);
            m3Power = (gamepad1.right_stick_y/4*rightTrigger*rightBumper);
            
            //Set the nudge controlls after the main so they dont get over written.
            if(gamepad1.y) {//Forward
                m0Power = -.16;
                m1Power = -.16;
                m2Power = -.16;
                m3Power = -.16;
            }
            else if(gamepad1.a) {//Backward
                m0Power = .16;
                m1Power = .16;
                m2Power = .16;
                m3Power = .16;
            }
            else if(gamepad1.b) {//Right
                m0Power = -.16;
                m1Power = .16;
                m2Power = -.16;
                m3Power = .16;
            }
            else if(gamepad1.x) {//Left
                m0Power = .16;
                m1Power = -.16;
                m2Power = .16;
                m3Power = -.16;
            }
            
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

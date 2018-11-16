package FTCFiles;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous



public class FTCAtom extends LinearOpMode {
    //Vars
    //Motors:
    public DcMotor FrontRight = null;
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
    
    //FrontRight = hardwareMap.get(DcMotor.class, "m0");

    // todo: write your code here
}

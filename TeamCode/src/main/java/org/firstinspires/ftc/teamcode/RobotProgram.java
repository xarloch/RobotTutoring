package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotProgram extends LinearOpMode
{
    DcMotor frontleft;
    DcMotor frontright;
    DcMotor backleft;
    DcMotor backright;

    DcMotor flywheel;
    DcMotor lauchAngle;

    Servo grabber;

    @Override
    public void runOpMode()
    {
        // initialize code
        frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        frontright = hardwareMap.get(DcMotor.class, "frontright");
        backleft = hardwareMap.get(DcMotor.class, "backleft");
        backright = hardwareMap.get(DcMotor.class, "backright");

        flywheel = hardwareMap.get(DcMotor.class, "flywheel");
        lauchAngle = hardwareMap.get(DcMotor.class, "launchAngle");

        grabber = hardwareMap.get(Servo.class, "grabber");

        waitForStart();

        if (opModeIsActive())
        {
            while (opModeIsActive())
            {

                // drive code
                double ly, lx, rx;
                ly = gamepad1.left_stick_y;
                lx = gamepad1.left_stick_x;
                rx = gamepad1.right_stick_x;

                frontleft.setPower( ly + rx + lx);
                frontright.setPower(ly - rx - lx);
                backleft.setPower(  ly + rx - lx);
                backright.setPower( ly - rx + lx);
            }
        }
    }
}

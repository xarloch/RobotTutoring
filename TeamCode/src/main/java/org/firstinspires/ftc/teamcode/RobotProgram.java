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
    DcMotor launchAngle;

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
        launchAngle = hardwareMap.get(DcMotor.class, "launchAngle");

        grabber = hardwareMap.get(Servo.class, "grabber");

        int grabberPos = 0;
        boolean canChangeGrabber = true;

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

                // lauching code
                double rt, lt;
                rt = gamepad2.right_trigger;
                lt = gamepad2.left_trigger;

                flywheel.setPower(rt - lt);

                // launch angle
                launchAngle.setPower(gamepad2.right_stick_y);

                // grabber
                // tell the servo how to move
                switch (grabberPos)
                {
                    case 0: // intake
                    {
                        grabber.setPosition(0.0);
                        break;
                    }

                    case 1: // hold
                    {
                        grabber.setPosition(0.5);
                        break;
                    }

                    case 2: // fire
                    {
                        grabber.setPosition(1.0);
                        break;
                    }

                    default:
                        break;
                }

                if (gamepad2.dpad_up || gamepad2.dpad_down)
                {
                    if (gamepad2.dpad_up && grabberPos < 2 && canChangeGrabber)
                    {
                        grabberPos += 1;
                    }

                    if (gamepad2.dpad_down && grabberPos > 0 && canChangeGrabber)
                    {
                        grabberPos -= 1;
                    }

                    canChangeGrabber = false;
                }
                else
                {
                    canChangeGrabber = true;
                }
            }
        }
    }
}

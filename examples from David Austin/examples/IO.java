package org.firstinspires.ftc.robotcontroller.david.austin.examples;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by David Austin on 10/27/2016.
 */

public class IO {
    DcMotor left1, left2, right1, right2, intake, shooter1, shooter2;
    GyroSensor gyro;
    Servo redFrontPush, redBackPush, blueFrontPush, blueBackPush, gate;
    OpticalDistanceSensor frontLightSensor, backLightSensor;
    double gyroOffset = 0;
    double leftOffset = 0, rightOffset = 0;
    double lastLeftEncoder = 0, lastRightEncoder = 0;
    double x = 0, y = 0;
    double COUNTSPERINCH = 84/1.28;
    public static int RED = 0, BLUE = 1;
    public static int allianceColor = RED;
    int beaconColor = RED;

    public static double redBackOut = 0.09;
    public static double redBackIn = 0.43;
    public static double redFrontOut = 0.5;
    public static double redFrontIn = 0.12;

    public static double blueBackOut = 0.69;
    public static double blueBackIn = 0.38;
    public static double blueFrontOut = 0.54;
    public static double blueFrontIn = 0.92;
    public static double gateClosed = 0;
    public static double gateOpen = 1;

    public IO(HardwareMap map) {
        left1 = map.dcMotor.get("left1");
        left2 = map.dcMotor.get("left2");
        right1 = map.dcMotor.get("right1");
        right2 = map.dcMotor.get("right2");
        left1.setDirection(DcMotorSimple.Direction.REVERSE);
        left2.setDirection(DcMotorSimple.Direction.REVERSE);
        intake = map.dcMotor.get("intake");
        shooter1 = map.dcMotor.get("shooter1");
        shooter2 = map.dcMotor.get("shooter2");

        gyro = map.gyroSensor.get("gyro");
        frontLightSensor = map.opticalDistanceSensor.get("frontLightSensor");
        backLightSensor = map.opticalDistanceSensor.get("backLightSensor");
        redBackPush = map.servo.get("redBackPush");
        redFrontPush = map.servo.get("redFrontPush");

        blueBackPush = map.servo.get("blueBackPush");
        blueFrontPush = map.servo.get("blueFrontPush");

        Servo capIntake = map.servo.get("capIntake");
        capIntake.setPosition(0.5);
        gate = map.servo.get("gate");

    }

    public void retractPushers() {
        redBackPush.setPosition(IO.redBackIn);
        redFrontPush.setPosition(IO.redFrontIn);
        blueBackPush.setPosition(IO.blueBackIn);
        blueFrontPush.setPosition(IO.blueFrontIn);
    }

    public void setGyroOffset() {
        gyroOffset = gyro.getHeading();
    }

    public void resetDriveEncoders() {
        leftOffset = left1.getCurrentPosition();
        rightOffset = right1.getCurrentPosition();
        lastLeftEncoder = 0;
        lastRightEncoder = 0;
    }
    public void updatePosition() {
        double leftEncoder = getLeftDriveEncoder();
        double rightEncoder = getRightDriveEncoder();
        double averageChange = (leftEncoder - lastLeftEncoder + rightEncoder - lastRightEncoder)/2.0;
        double heading = Math.toRadians(getHeading());
        x += averageChange * Math.cos(heading);
        y += averageChange * Math.sin(heading);
        lastLeftEncoder = leftEncoder;
        lastRightEncoder = rightEncoder;
    }
    public double getX() {
        return x / COUNTSPERINCH;
    }
    public double getY() {
        return y / COUNTSPERINCH;
    }

    public double getHeading() {
        double heading = gyro.getHeading() - gyroOffset;
        while (heading > 180) {
            heading -= 360;
        }
        while (heading <= - 180) {
            heading += 360;
        }
        return -heading;
    }

    public void openGate() {
        //gate.setPosition(gateOpen);
    }
    public void closeGate() {
        //gate.setPosition(gateClosed);
    }

    public double getLeftDriveEncoder() {
        return left1.getCurrentPosition() - leftOffset;
    }

    public double getRightDriveEncoder() {
        return right1.getCurrentPosition() - rightOffset;
    }

    public void setDrivePower(double left, double right) {
        left1.setPower(left);
        left2.setPower(left);
        right1.setPower(right);
        right2.setPower(right);
    }

    public double getFrontLightReading(){
        return frontLightSensor.getLightDetected();
    }

    public double getBackLightReading(){
        return backLightSensor.getLightDetected();
    }

    public void setBeaconColor(int color){
        beaconColor = color;
    }

    public int getBeaconColor(){
        return beaconColor;
    }
    public static void setAllianceColor(int color){
        allianceColor = color;
    }
    public static int getAllianceColor(){
        return allianceColor;
    }

    public void setRedBeaconPushers(double front, double back) {
        redBackPush.setPosition(back);
        redFrontPush.setPosition(front);
    }

    public void setBlueBeaconPushers(double front, double back) {
        blueBackPush.setPosition(back);
        blueFrontPush.setPosition(front);
    }

    public void setIntakePower(double power) {
        intake.setPower(-power);
    }

    public void setShooterPower(double power) {
        shooter1.setPower(power);
        shooter2.setPower(-power);
    }
}

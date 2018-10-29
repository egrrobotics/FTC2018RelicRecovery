package org.firstinspires.ftc.robotcontroller.david.austin.examples;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utilities.IO;

/**
 * Created by David Austin on 10/27/2016.
 */

public class BasicCommand {
    static IO io;
    static Telemetry telemetry;
    public static void setIO(IO i) {
        io = i;
    }
    public static void setTelemetry(Telemetry tele) {
        telemetry = tele;
    }

    public void init(){
    }

    public void execute(){
    }

    public void stop(){
    }

    public boolean isFinished(){
        return false;
    }
}

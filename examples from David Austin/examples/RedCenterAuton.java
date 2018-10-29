package org.firstinspires.ftc.robotcontroller.david.austin.examples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.DriveForward;
import org.firstinspires.ftc.teamcode.commands.Rotate;

/**
 * Created by David Austin on 11/10/2016.
 */

@Autonomous(name="RedCenter",group="Auton")
public class RedCenterAuton extends RedAuton {
    public void addFinalCommands() {
        double angle = -70;
        commands.add(new Rotate(angle,0.9,0));
        commands.add(new DriveForward(25, DriveForward.YLESSTHAN, 0.7, angle));
        double finalAngle = -135;
        commands.add(new Rotate(finalAngle,0.9,0));
        commands.add(new DriveForward(0, DriveForward.YLESSTHAN, 0.7, finalAngle));
    }
}

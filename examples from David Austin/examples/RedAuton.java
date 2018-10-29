package org.firstinspires.ftc.robotcontroller.david.austin.examples;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.CommandGroup;
import org.firstinspires.ftc.teamcode.commands.DriveForward;
import org.firstinspires.ftc.teamcode.commands.DriveForwardForDistance;
import org.firstinspires.ftc.teamcode.commands.DriveHorizontal;
import org.firstinspires.ftc.teamcode.commands.PressButtons;
import org.firstinspires.ftc.teamcode.commands.ReadCamera;
import org.firstinspires.ftc.teamcode.commands.Rotate;
import org.firstinspires.ftc.teamcode.commands.ShootParticles;
import org.firstinspires.ftc.teamcode.commands.StopAtLine;
import org.firstinspires.ftc.teamcode.commands.WaitForTime;
import org.firstinspires.ftc.teamcode.utilities.IO;

/**
 * Created by David Austin on 11/10/2016.
 */
public abstract class RedAuton extends FirstAuton {
    public RedAuton() {
        super();
        allianceColor = IO.RED;
    }

    @Override
    public void addCommands() {
        io.setAllianceColor(IO.RED);
        CommandGroup group = new CommandGroup();
        group.addCommand(new DriveHorizontal(13,-2,0.5));
        group.addCommand(new ShootParticles());
        commands.add(group);
        commands.add(new Rotate(38,0,.7));
        commands.add(new WaitForTime(100));
        commands.add(new DriveForward(47,DriveForward.YGREATERTHAN,.55,38));
        commands.add(new Rotate(0,.6,0.6));
        commands.add(new WaitForTime(100));
        double ycoord = 49.5;
        commands.add(new DriveHorizontal(90, ycoord, 0.5,true));
        commands.add(new StopAtLine(112,ycoord,0.2,StopAtLine.FRONT, true));
        commands.add(new DriveForwardForDistance(3.5,ycoord,0.25));

        commands.add(new WaitForTime(500));
        group = new CommandGroup();
        group.addCommand(new ReadCamera());
        group.addCommand(new DriveForwardForDistance(2, ycoord, 0.25));
        //commands.add(group);
        commands.add(new ReadCamera());
        commands.add(new DriveForwardForDistance(2,ycoord,0.25));
        commands.add(new PressButtons());

        //commands.add(new WaitForTime(100));
        commands.add(new DriveHorizontal(70,48,-.6,true)); // was 70
        commands.add(new StopAtLine(36,48,-.2, StopAtLine.FRONT));
        commands.add(new WaitForTime(500)); // was 500
        commands.add(new DriveForwardForDistance(5.8,48,0.25));

        commands.add(new WaitForTime(500));
        group = new CommandGroup();
        group.addCommand(new ReadCamera());
        group.addCommand(new DriveForwardForDistance(1.75, 48, 0.2));
        commands.add(group);
        commands.add(new PressButtons());

        //commands.add(new WaitForTime(1500));

    }
}

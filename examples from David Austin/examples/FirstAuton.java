package org.firstinspires.ftc.robotcontroller.david.austin.examples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcontroller.internal.HSCamera;
import org.firstinspires.ftc.teamcode.commands.*;
import org.firstinspires.ftc.teamcode.utilities.*;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by David Austin on 10/27/2016.
 */

public abstract class FirstAuton extends OpMode {
    IO io;
    static final int INIT = 0;
    static final int EXECUTE = 1;
    static final int STOP = 2;
    static final int FINISHED = 3;
    int state;
    ArrayList<BasicCommand> commands;
    BasicCommand currentCommand;
    Iterator<BasicCommand> iterator;
    int allianceColor = IO.RED;
    public void init() {
        io = new IO(hardwareMap);
        io.retractPushers();
        HSCamera.setHardwareMap(hardwareMap);

        //io.setAllianceColor(allianceColor);
        BasicCommand.setIO(io);
        BasicCommand.setTelemetry(telemetry);

        commands = new ArrayList<BasicCommand>();
        addCommands();
        addFinalCommands();
        iterator = commands.iterator();
        currentCommand = iterator.next();
        state = INIT;
    }

    public void start() {
        io.setGyroOffset();
        io.resetDriveEncoders();
    }
    public abstract void addCommands();
    public abstract void addFinalCommands();

    public void loop() {
        io.updatePosition();
        switch(state){
            case INIT:
                currentCommand.init();
                state = EXECUTE;
                break;
            case EXECUTE:
                if(currentCommand.isFinished()){
                    currentCommand.stop();
                    if (iterator.hasNext()) {
                        currentCommand = iterator.next();
                        state = INIT;
                    } else state = FINISHED;
                    break;
                }
                currentCommand.execute();
                break;
            case STOP:
                currentCommand.stop();
                if(iterator.hasNext()){
                    currentCommand = iterator.next();
                    state = INIT;
                }else state = FINISHED;
                break;
            case FINISHED:
                break;

        }
    }

    public void stop() {
        io.setDrivePower(0,0);
    }

}

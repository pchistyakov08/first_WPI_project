// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.WheelSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class TurnToPositionCommand extends Command {
    private PIDController p1; 
    private double position;
    /**
     * Creates a new ExampleCommand.
     */
    public TurnToPositionCommand(double position) {
        setName("ExampleCommand");
        this.position = position; 
        p1 = new PIDController(1, 0, 0);
        p1.setTolerance(3);
        p1.enableContinuousInput(0, 360);
        
        

        
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(WheelSubsystem.getInstance());
    }


    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        p1.reset();
        
    }


    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double speed = p1.calculate(WheelSubsystem.getInstance().getPosition(),position);
        WheelSubsystem.getInstance().setSpeedForTurningMotor(speed);
       
    }


    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        WheelSubsystem.getInstance().setSpeedForDrivingMotor(0);
    }


    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return p1.atSetpoint();
    }
}

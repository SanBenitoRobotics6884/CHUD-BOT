// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Optional;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.MultiTargetPNPResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.wpilibj.shuffleboard.LayoutType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {
  PhotonCamera m_visionCamera = new PhotonCamera(getName());
  AprilTagFieldLayout m_aprilTagFieldLayout = AprilTagFieldLayout.loadField(AprilTagFields.k2026RebuiltWelded);

  /** Creates a new VisionSubsystem. */
  public VisionSubsystem() {}

  @Override
  public void periodic() {
    var results = m_visionCamera.getAllUnreadResults();
    PhotonTrackedTarget bestTarget;
    if (!results.isEmpty()) {
      var result = results.get(results.size()-1);
      // If targets are seen
      if (result.hasTargets()) {
        bestTarget = result.getBestTarget();
        if (m_aprilTagFieldLayout.getTagPose(bestTarget.getFiducialId()).isPresent()) {
          Pose3d robotPose = PhotonUtils.estimateFieldToRobotAprilTag(bestTarget.getBestCameraToTarget(), bestTarget.get, null);
        }

      if (result.getMultiTagResult().isPresent()) { // Assuming more than one tag is seen, obtain the multitag result.
       //result.getMultiTagResult();
      }
    }
    // This method will be called once per scheduler run
  }


}

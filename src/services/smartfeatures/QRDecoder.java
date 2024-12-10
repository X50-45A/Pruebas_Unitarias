package services.smartfeatures;

import micromobility.PMVehicle;

import java.awt.image.BufferedImage;

public interface QRDecoder { // Decodes QR codes from an image
    PMVehicle getVehicleID(BufferedImage QRImg) throws CorruptedImgException;
}


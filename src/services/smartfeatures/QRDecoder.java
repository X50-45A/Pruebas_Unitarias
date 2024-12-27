package services.smartfeatures;

import micromobility.PMVehicle;

import java.awt.image.BufferedImage;
import exceptions.*;
public interface QRDecoder { // Decodes QR codes from an image
    PMVehicle getVehicleID(BufferedImage QRImg) throws CorruptedImgException;
}


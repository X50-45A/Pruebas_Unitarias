package services.smartfeatures;

public interface QRDecoder { // Decodes QR codes from an image
    VehicleID getVehicleID(BufferedImage QRImg) throws CorruptedImgException;
}


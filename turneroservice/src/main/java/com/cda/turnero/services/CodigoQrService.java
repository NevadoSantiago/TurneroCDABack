package com.cda.turnero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.CodigoQrDao;
import com.cda.turnero.model.CodigoQr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class CodigoQrService {

	@Autowired
	CodigoQrDao codigoQrDao;
	
	public CodigoQr generarCodigoQr(String datos) {
		CodigoQr codigo = new CodigoQr();
		codigo.setEnable(true);
		codigo.setCodigo(datos);
		return codigoQrDao.saveAndFlush(codigo);
	}
	
	
	public byte[] showQRCodeImage(String data) {
		try {
        	return getQRCodeImage(data, 400, 400);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }
		
		return null;
	}
    
    /* 
    This method takes the text to be encoded, the width and height of the QR Code, 
    and returns the QR Code in the form of a byte array.
    */
    private static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray(); 
        return pngData;
    }

	public CodigoQr getById(Integer idQr) {
		return codigoQrDao.findById(idQr).get();
	}

	public void updateQr(CodigoQr codigoQr) {
		codigoQrDao.save(codigoQr);
		
	}
	
}

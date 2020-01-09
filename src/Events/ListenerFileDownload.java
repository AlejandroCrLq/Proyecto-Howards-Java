package Events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import javax.mail.MessagingException;
import javax.mail.Part;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import interfaces.MailReadWindow;

/**
 * La clase DescargaArchivoListener se encarga de
 *
 * @author: Jorge Rico Vivas
 * @version: 1.0
 * @since: 17/12/2019
 */

public class ListenerFileDownload implements MouseListener, KeyListener {

	private JList<Part> listFiles;
	private MailReadWindow mailRead;

	public ListenerFileDownload(JList<Part> listFiles, MailReadWindow read) {
		this.listFiles = listFiles;
		mailRead = read;
	}

	private void download() {
		mailRead.setEnabled(false);
		if (listFiles.getSelectedIndices().length == 1) {
			try {
				Part part = listFiles.getModel().getElementAt(listFiles.getSelectedIndex());
				JFileChooser fileChooser = new JFileChooser();
				String fileName = part.getFileName();
				fileChooser.setDialogTitle("Indique donde y como guardar el archivo: " + fileName);
				String extension = null;
				if (fileName.contains(".")) {
					extension = fileName.substring(fileName.lastIndexOf("."));
				}
				int userSelection = fileChooser.showSaveDialog(new JFrame());
				File fileToSave = fileChooser.getSelectedFile();
				if (!fileToSave.getName().contains(".") && (extension != null)) {
					fileToSave = new File(fileToSave.getAbsolutePath() + extension);
				}
				boolean replaced = false;
				if (fileToSave.exists()) {
					replaced = true;
				}

				if (userSelection == JFileChooser.APPROVE_OPTION) {
					if (!fileToSave.exists()) {
						try {
							fileToSave.createNewFile();
						} catch (IOException e1) {
							System.out.println("Couldn't save file");
						}
					}
					try {
						InputStream input = part.getInputStream();
						FileOutputStream fos = new FileOutputStream(fileToSave);

						byte[] buf = new byte[4096];
						int bytesRead;
						while ((bytesRead = input.read(buf)) != -1) {
							fos.write(buf, 0, bytesRead);
						}
						if (replaced) {
							JOptionPane.showMessageDialog(new JFrame(), "Archivo reemplazado y descargado");
						} else {
							JOptionPane.showMessageDialog(new JFrame(), "Archivo descargado");
						}
					} catch (IOException | MessagingException e1) {
						System.out.println("Ha sucedido un error");
					}

				}

			} catch (NullPointerException e) {

			} catch (MessagingException e) {
			}

		} else {
			try {
				ArrayList<Part> parts = new ArrayList<Part>();
				for (int index : listFiles.getSelectedIndices()) {
					parts.add(listFiles.getModel().getElementAt(index));
				}
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Indique donde y como guardar el comprimidos de los archivos seleccionados");
				String extension = ".zip";
				int userSelection = fileChooser.showSaveDialog(new JFrame());
				File fileToSave = fileChooser.getSelectedFile();
				if (!fileToSave.getName().contains(".") && (extension != null)) {
					fileToSave = new File(fileToSave.getAbsolutePath() + extension);
				}
				boolean replaced = false;
				if (fileToSave.exists()) {
					replaced = true;
				}

				if (userSelection == JFileChooser.APPROVE_OPTION) {
					if (!fileToSave.exists()) {
						try {
							fileToSave.createNewFile();
						} catch (IOException e1) {
							System.out.println("Couldn't save file");
						}
					}
					try {

						ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(fileToSave));

						for (Part part : parts) {
							try {
								zip.putNextEntry(new ZipEntry(part.getFileName()));
							} catch (ZipException ex) {
								int index = 1;
								while (true) {
									String fileName = part.getFileName();
									String extensionNow = "";
									if (fileName.contains(".")) {
										extensionNow = fileName.substring(fileName.lastIndexOf("."));
										fileName = fileName.substring(0, fileName.lastIndexOf("."));
									}
									try {
										zip.putNextEntry(new ZipEntry(fileName + "(" + index + ")" + extensionNow));
										break;
									} catch (ZipException exc) {
										index++;
									}
								}
							}
							InputStream input = part.getInputStream();
							byte[] buf = new byte[4096];
							int bytesRead;
							while ((bytesRead = input.read(buf)) != -1) {
								zip.write(buf, 0, bytesRead);
							}
						}
						zip.close();

						if (replaced) {
							JOptionPane.showMessageDialog(new JFrame(), "Archivo reemplazado y descargado");
						} else {
							JOptionPane.showMessageDialog(new JFrame(), "Archivo descargado");
						}
					} catch (IOException | MessagingException e1) {
						System.out.println("Ha sucedido un error");
						e1.printStackTrace();
					}

				}

			} catch (NullPointerException e) {
			}
		}
		mailRead.setEnabled(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			download();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			download();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
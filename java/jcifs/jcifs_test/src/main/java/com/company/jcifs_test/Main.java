package com.company.jcifs_test;

import java.io.Console;
import java.net.MalformedURLException;

import jcifs.CIFSContext;
import jcifs.CIFSException;
import jcifs.config.PropertyConfiguration;
import jcifs.context.BaseContext;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

public class Main {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java Main <domain> <username> <share_url>");
            return;
        }

        String domain = args[0];
        String username = args[1];
        String shareUrl = args[2];

        Console console = System.console();
        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        char[] passwordArray = console.readPassword("Enter password: ");
        String password = new String(passwordArray);

        try {
            PropertyConfiguration config = new PropertyConfiguration(System.getProperties());
            BaseContext jcifsContext = new BaseContext(config);

            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(jcifsContext, domain, username, password);

            CIFSContext base = jcifsContext.withCredentials(auth);
            SmbFile smbFile = new SmbFile(shareUrl, base);
            for (SmbFile file : smbFile.listFiles()) {
                System.out.println("File: " + file.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            java.util.Arrays.fill(passwordArray, ' ');
        }
    }

}


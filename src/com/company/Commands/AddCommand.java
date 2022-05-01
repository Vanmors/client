package com.company.Commands;

import com.company.data.Coordinates;
import com.company.data.Flat;
import com.company.data.House;
import com.company.data.View;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.ZonedDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddCommand implements Serializable {
    private static final long serialVersionUID = 1L;
    private Flat f;

    public AddCommand(Flat f) {
        this.f = f;
    }
}




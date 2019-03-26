#![feature(process_exitcode_placeholder)]

use std::env;
use std::net::{Ipv4Addr, UdpSocket};
use std::process::ExitCode;

const BUFFER_SIZE: usize = 1024;

fn main() -> Result<(), ExitCode> {
    let port = match env::args().nth(1) {
        Some(port) => port.parse().map_err(|err| {
            eprintln!("invalid port number: {}", err);
            ExitCode::FAILURE
        }),
        None => {
            eprintln!("missing port number");
            Err(ExitCode::FAILURE)
        }
    }?;

    let socket =
        UdpSocket::bind(("127.0.0.1".parse::<Ipv4Addr>().unwrap(), port)).map_err(|err| {
            eprintln!("failed to open socket: {}", err);
            ExitCode::FAILURE
        })?;

    let mut buffer = [0; BUFFER_SIZE];
    let (count, addr) = socket.recv_from(&mut buffer).map_err(|err| {
        eprintln!("failed to receive datagram: {}", err);
        ExitCode::FAILURE
    })?;

    println!("{}", String::from_utf8_lossy(&buffer[..count]));
    println!("{}", addr);

    Ok(())
}

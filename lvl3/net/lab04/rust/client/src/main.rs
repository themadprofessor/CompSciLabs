#![feature(process_exitcode_placeholder)]

use std::env;
use std::net::{Ipv4Addr, SocketAddrV4, UdpSocket};
use std::process::ExitCode;

fn main() -> Result<(), ExitCode> {
    let address: SocketAddrV4 = match env::args().nth(1) {
        Some(port) => port.parse().map_err(|err| {
            eprintln!("invalid address: {}", err);
            ExitCode::FAILURE
        }),
        None => {
            eprintln!("missing address");
            Err(ExitCode::FAILURE)
        }
    }?;

    let socket = UdpSocket::bind(("127.0.0.1".parse::<Ipv4Addr>().unwrap(), 10000))
        .map_err(|err| {
            eprintln!("failed to bind socket: {}", err);
            ExitCode::FAILURE
        })?;

    socket
        .send_to("Hello World".as_ref(), address)
        .map_err(|err| {
            eprintln!("failed to send data: {}", err);
            ExitCode::FAILURE
        })
        .map(|_| ())
}

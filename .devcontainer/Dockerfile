FROM ghcr.io/crystal-linux/crystal:latest

RUN pacman -S --needed --noconfirm bat fish zellij git jdk17-openjdk gradle
RUN useradd -m -G wheel vscode
RUN  bash -c "echo \"vscode ALL=(ALL) NOPASSWD: ALL\" >> /etc/sudoers"

ENV USER=vscode
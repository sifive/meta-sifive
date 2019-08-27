DESCRIPTION = "SiFive RISC-V Core IP Demo Benchmarks Linux image"

IMAGE_FEATURES += "\
    splash \
    ssh-server-openssh \
    tools-sdk \
    tools-debug \
    tools-profile \
    doc-pkgs \
    dev-pkgs \
    dbg-pkgs \
    nfs-client \
    nfs-server \
    x11-base"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-core-full-cmdline \
    kernel-modules \
    kernel-devsrc \
    kernel-dev \
    sysstat \
    dhrystone \
    whetstone \
    iperf3 \
    iperf2 \
    fio \
    tinymembench \
    sysbench \
    memtester \
    lmbench \
    vim \
    nano \
    mc \
    chrony \
    curl \
    wget \
    git \
    bind-utils \
    networkmanager \
    networkmanager-nmtui \
    networkmanager-nmtui-doc \
    haveged \
    e2fsprogs-resize2fs \
    e2fsprogs-e2fsck \
    e2fsprogs-mke2fs \
    parted \
    gptfdisk \
    rsync \
    screen \
    tmux \
    stress-ng \
    packagegroup-core-x11 \
    packagegroup-xfce-base \
    packagegroup-xfce-extended \
    xrandr \
    pciutils \
    usbutils \
    devmem2 \
    mtd-utils \
    mesa-demos \
    read-edid \
    xscreensaver \
    mesa-megadriver \
    xserver-xorg-utils \
    xserver-xorg-xvfb \
    xserver-xorg-extension-dbe \
    xserver-xorg-extension-dri \
    xserver-xorg-extension-dri2 \
    xserver-xorg-extension-extmod \
    xserver-xorg-extension-glx \
    xserver-xorg-extension-record \
    python3-ctypes \
    xf86-video-ati \
    linux-firmware \
    quake2 \
    dhcp-client \
    nbd-client \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    "

inherit core-image distro_features_check

REQUIRED_DISTRO_FEATURES = "\
    x11 \
    systemd"

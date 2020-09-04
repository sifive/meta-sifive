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
    nfs-server"

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
    hexedit \
    pv \
    lsof \
    libgpiod \
    libgpiod-tools \
    libgpiod-dev \
    i2c-tools \
    i2c-tools-misc \
    spitools \
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
    dhcpcd \
    nbd-client \
    mpfr-dev \
    gmp-dev \
    libmpc-dev \
    zlib-dev \
    flex \
    bison \
    dejagnu \
    gettext \
    texinfo \
    procps \
    sln \
    glibc-dev \
    glibc-utils \
    glibc-staticdev \
    elfutils \
    elfutils-dev \
    elfutils-binutils \
    elfutils-staticdev \
    libasm \
    libdw \
    libelf \
    pciutils \
    usbutils \
    devmem2 \
    mtd-utils \
    sysfsutils \
    htop \
    nvme-cli \
    python3 \
    cmake \
    ninja \
    python3-scons \
    libatomic-dev \
    libatomic-staticdev \
    libgomp-dev \
    libgomp-staticdev \
    libstdc++-dev \
    libstdc++-staticdev \
    dtc \
    pcimem \
    jq \
    hdparm \
    udev-extraconf \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    "

IMAGE_INSTALL_append_freedom-u540 = "\
    unleashed-udev-rules \
    "

inherit core-image extrausers

EXTRA_USERS_PARAMS = "usermod -P sifive root;"

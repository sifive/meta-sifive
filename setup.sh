#!/bin/bash
# Bootstrapper for buildbot slave

DIR="build"
MACHINE="freedom-u540"
CONFFILE="conf/auto.conf"
BITBAKEIMAGE="demo-coreip-cli"

# clean up the output dir
#echo "Cleaning build dir"
#rm -rf $DIR

# make sure sstate is there
#echo "Creating sstate directory"
#mkdir -p ~/sstate/$MACHINE

# fix permissions set by buildbot
#echo "Fixing permissions for buildbot"
#umask -S u=rwx,g=rx,o=rx
#chmod -R 755 .

# Reconfigure dash on debian-like systems
which aptitude > /dev/null 2>&1
ret=$?
if [ "$(readlink /bin/sh)" = "dash" -a "$ret" = "0" ]; then
  sudo aptitude install expect -y
  expect -c 'spawn sudo dpkg-reconfigure -freadline dash; send "n\n"; interact;'
elif [ "${0##*/}" = "dash" ]; then
  echo "dash as default shell is not supported"
  return
fi
# bootstrap OE
echo "Init OE"
export BASH_SOURCE="openembedded-core/oe-init-build-env"
. ./openembedded-core/oe-init-build-env $DIR

if [ -e $CONFFILE ]; then
    echo "Your build directory already has local configuration file!"
    echo "If you want to start from scratch remove old build directory:"
    echo ""
    echo "    rm -rf $PWD"
    echo ""
else

# Symlink the cache
#echo "Setup symlink for sstate"
#ln -s ~/sstate/${MACHINE} sstate-cache

# add the missing layers
echo "Adding layers"
bitbake-layers add-layer ../meta-openembedded/meta-oe
bitbake-layers add-layer ../meta-openembedded/meta-python
bitbake-layers add-layer ../meta-openembedded/meta-multimedia
bitbake-layers add-layer ../meta-openembedded/meta-networking
bitbake-layers add-layer ../meta-openembedded/meta-gnome
bitbake-layers add-layer ../meta-openembedded/meta-xfce
bitbake-layers add-layer ../meta-riscv
bitbake-layers add-layer ../meta-sifive

# fix the configuration
echo "Creating auto.conf"

#if [ -e $CONFFILE ]; then
#    rm -rf $CONFFILE
#fi
cat <<EOF > $CONFFILE
MACHINE ?= "${MACHINE}"
#IMAGE_FEATURES += "tools-debug"
#IMAGE_FEATURES += "tools-tweaks"
#IMAGE_FEATURES += "dbg-pkgs"
# rootfs for debugging
#IMAGE_GEN_DEBUGFS = "1"
#IMAGE_FSTYPES_DEBUGFS = "tar.gz"
#EXTRA_IMAGE_FEATURES_append = " ssh-server-dropbear"
EXTRA_IMAGE_FEATURES_append = " package-management"
PACKAGECONFIG_append_pn-qemu-native = " sdl"
PACKAGECONFIG_append_pn-nativesdk-qemu = " sdl"
USER_CLASSES ?= "buildstats buildhistory buildstats-summary image-mklibs image-prelink"

require conf/distro/include/no-static-libs.inc
require conf/distro/include/yocto-uninative.inc
require conf/distro/include/security_flags.inc

INHERIT += "uninative"

DISTRO_FEATURES_append = " largefile opengl ptest multiarch pam systemd vulkan "
DISTRO_FEATURES_BACKFILL_CONSIDERED += "sysvinit"
VIRTUAL-RUNTIME_init_manager = "systemd"
HOSTTOOLS_NONFATAL_append = " ssh"

# Disable broken bbappend in meta-riscv layer
BBMASK += "openssl_1.1.1e.bbappend"

# We use NetworkManager instead
PACKAGECONFIG_remove_pn-systemd = "networkd"

# Disable security flags for bootloaders
# Security flags incl. smatch protector which is not supported in these packages
SECURITY_CFLAGS_pn-freedom-u540-c000-bootloader = ""
SECURITY_LDFLAGS_pn-freedom-u540-c000-bootloader = ""

SECURITY_CFLAGS_pn-opensbi = ""
SECURITY_LDFLAGS_pn-opensbi = ""

# We need at least 10.0.0 for mesa to support modern AMD GPUs
LLVMVERSION = "10.0.0"

# Add r600 drivers for AMD GPU
PACKAGECONFIG_append_pn-mesa = " r600"

# Add support for modern AMD GPU (e.g. RX550 / POLARIS)
PACKAGECONFIG_append_pn-mesa = " radeonsi"
PACKAGECONFIG_append_pn-mesa = " gallium-llvm"

EOF
fi

echo "---------------------------------------------------"
echo "MACHINE=${MACHINE} bitbake ${BITBAKEIMAGE}"
echo "---------------------------------------------------"
echo ""
echo "Buildable machine info"
echo "---------------------------------------------------"
echo "* freedom-u540: The SiFive HiFive Unleashed board"
echo "* qemuriscv64: The 64-bit RISC-V machine"
echo "---------------------------------------------------"

# start build
#echo "Starting build"
#bitbake $BITBAKEIMAGE


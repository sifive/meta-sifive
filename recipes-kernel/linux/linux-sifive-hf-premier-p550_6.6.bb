DESCRIPTION = "SiFive HiFive Premier P550 Board Linux Kernel"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

KBRANCH = "dev/kernel/v6.6-hifive-premier-p550"
SRCREV = "4523280804a749f3ff9d3188b72c5f819ca27bef"

KCONFIG_MODE = "--alldefconfig"

KBUILD_DEFCONFIG:hifive-premier-p550 ?= ""

KERNEL_FEATURES:remove = "cfg/fs/vfat.scc"
KERNEL_FEATURES:remove = "features/debug/printk.scc"
KERNEL_FEATURES:remove = "features/kernel-sample/kernel-sample.scc"

require recipes-kernel/linux/linux-yocto.inc

SRC_URI = "git://git@github.com/sifive/linux-kernel-hf-prem.git;protocol=ssh;branch=${KBRANCH}"
SRC_URI:append = " file://wifi.cfg \
                 "
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
LINUX_VERSION ?= "6.6.x"
LINUX_VERSION_EXTENSION = ""
KERNEL_VERSION_SANITY_SKIP="1"

PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE = "(hifive-premier-p550)"

DESCRIPTION = "Mainline Linux Kernel"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

LINUX_VERSION_EXTENSION = ""

PV = "${LINUX_VERSION}+git${SRCPV}"

KCONFIG_MODE = "--alldefconfig"

KBUILD_DEFCONFIG = ""
KBUILD_DEFCONFIG:freedom-u540 = "defconfig"
KBUILD_DEFCONFIG:qemuriscv64 = "defconfig"
KBUILD_DEFCONFIG:unmatched = "defconfig"

COMPATIBLE_MACHINE = "(freedom-u540|qemuriscv64|unmatched)"

KERNEL_FEATURES:remove = "cfg/fs/vfat.scc"
KERNEL_FEATURES:remove = "features/debug/printk.scc"
KERNEL_FEATURES:remove = "features/kernel-sample/kernel-sample.scc"

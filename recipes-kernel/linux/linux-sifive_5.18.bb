DESCRIPTION = "SiFive Linux Kernel"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"

KBRANCH ?= "linux-5.18.y"
KBRANCH:freedom-u-540 ?= "linux-5.18.y"
KBRANCH:qemuriscv64 ?= "linux-5.18.y"
KBRANCH:unmatched ?= "linux-5.18.y"

SRCREV ?= "3740a5da82ebec7a6d8f3a6deea77b8129c8c2ee"
SRCREV:freedom-u-540 ?= "3740a5da82ebec7a6d8f3a6deea77b8129c8c2ee"
SRCREV:qemuriscv64 ?= "3740a5da82ebec7a6d8f3a6deea77b8129c8c2ee"
SRCREV:unmatched ?= "3740a5da82ebec7a6d8f3a6deea77b8129c8c2ee"

KCONFIG_MODE = "--alldefconfig"

KBUILD_DEFCONFIG ?= ""
KBUILD_DEFCONFIG:freedom-u540 ?= "defconfig"
KBUILD_DEFCONFIG:qemuriscv64 ?= "defconfig"
KBUILD_DEFCONFIG:unmatched ?= "defconfig"

KERNEL_EXTRA_FEATURES ?= ""
KERNEL_FEATURES:remove = "cfg/fs/vfat.scc"
KERNEL_FEATURES:remove = "features/debug/printk.scc"
KERNEL_FEATURES:remove = "features/kernel-sample/kernel-sample.scc"

require recipes-kernel/linux/linux-yocto.inc

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;branch=${KBRANCH}"

SRC_URI:append:riscv64 = " \
    file://0001-riscv-sifive-fu740-cpu-1-2-3-4-set-compatible-to-sif.patch \
    file://0003-riscv-sifive-unmatched-define-PWM-LEDs.patch \
    file://0005-SiFive-HiFive-Unleashed-Add-PWM-LEDs-D1-D2-D3-D4.patch \
    file://Revert-riscv-dts-sifive-unmatched-Link-the-tmp451-wi.patch \
"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
LINUX_VERSION ?= "5.18.15"
LINUX_VERSION_EXTENSION = ""

PV = "${LINUX_VERSION}+git${SRCPV}"

KCONF_BSP_AUDIT_LEVEL = "2"
KCONF_AUDIT_LEVEL = "2"

COMPATIBLE_MACHINE = "(freedom-u540|qemuriscv64|unmatched)"

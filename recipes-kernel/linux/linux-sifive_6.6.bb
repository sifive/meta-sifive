DESCRIPTION = "SiFive Linux Kernel"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

KBRANCH ?= "linux-6.6.y"
KBRANCH:freedom-u-540 ?= "linux-6.6.y"
KBRANCH:qemuriscv64 ?= "linux-6.6.y"
KBRANCH:unmatched ?= "linux-6.6.y"

SRCREV_machine ?= "47345b4264bc394a8d16bb16e8e7744965fa3934"
SRCREV_machine:freedom-u-540 ?= "47345b4264bc394a8d16bb16e8e7744965fa3934"
SRCREV_machine:qemuriscv64 ?= "47345b4264bc394a8d16bb16e8e7744965fa3934"
SRCREV_machine:unmatched ?= "47345b4264bc394a8d16bb16e8e7744965fa3934"
SRCREV_meta ?= "48f7c852bd375b9340c68897ccd87ad89ead5f38"

KCONFIG_MODE = "--alldefconfig"

KBUILD_DEFCONFIG ?= ""
KBUILD_DEFCONFIG:freedom-u540 ?= "defconfig"
KBUILD_DEFCONFIG:qemuriscv64 ?= "defconfig"
KBUILD_DEFCONFIG:unmatched ?= ""

KERNEL_EXTRA_FEATURES ?= ""
KERNEL_FEATURES:append:qemuriscv64 = " cfg/virtio.scc"
KERNEL_FEATURES:remove = "cfg/fs/vfat.scc"
KERNEL_FEATURES:remove = "features/debug/printk.scc"
KERNEL_FEATURES:remove = "features/kernel-sample/kernel-sample.scc"

require recipes-kernel/linux/linux-yocto.inc

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;name=machine;branch=${KBRANCH} \
           git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=yocto-6.6;destsuffix=${KMETA}"

SRC_URI:append:unmatched = " file://defconfig"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
LINUX_VERSION ?= "6.6.12"
LINUX_VERSION_EXTENSION = ""

PV = "${LINUX_VERSION}+git${SRCPV}"

KMETA = "kernel-meta"
KCONF_BSP_AUDIT_LEVEL = "2"
KCONF_AUDIT_LEVEL = "2"
KCONF_BSP_AUDIT_LEVEL:qemuriscv64 = "1"
KCONF_AUDIT_LEVEL:qemuriscv64 = "1"

COMPATIBLE_MACHINE = "(freedom-u540|qemuriscv64|unmatched)"

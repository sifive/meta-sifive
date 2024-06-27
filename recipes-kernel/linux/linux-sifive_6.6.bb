DESCRIPTION = "SiFive Linux Kernel"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

KBRANCH ?= "linux-6.6.y"
KBRANCH:freedom-u-540 ?= "linux-6.6.y"
KBRANCH:qemuriscv64 ?= "linux-6.6.y"
KBRANCH:unmatched ?= "linux-6.6.y"

SRCREV_machine ?= "91de249b6804473d49984030836381c3b9b3cfb0"
SRCREV_machine:freedom-u-540 ?= "91de249b6804473d49984030836381c3b9b3cfb0"
SRCREV_machine:qemuriscv64 ?= "91de249b6804473d49984030836381c3b9b3cfb0"
SRCREV_machine:unmatched ?= "91de249b6804473d49984030836381c3b9b3cfb0"
SRCREV_meta ?= "66bebb6789d02e775d4c93d7ca4bf79c2ead4b28"

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
LINUX_VERSION ?= "6.6.32"
LINUX_VERSION_EXTENSION = ""

PV = "${LINUX_VERSION}+git${SRCPV}"

KMETA = "kernel-meta"
KCONF_BSP_AUDIT_LEVEL = "2"
KCONF_AUDIT_LEVEL = "2"
KCONF_BSP_AUDIT_LEVEL:qemuriscv64 = "1"
KCONF_AUDIT_LEVEL:qemuriscv64 = "1"

COMPATIBLE_MACHINE = "(freedom-u540|qemuriscv64|unmatched)"

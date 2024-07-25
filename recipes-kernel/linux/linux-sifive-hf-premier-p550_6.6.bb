DESCRIPTION = "SiFive HiFive Premier P550 Board Linux Kernel"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

KBRANCH ?= "dev/kernel/v6.6-hifive-premier-p550"
KBRANCH:hifive-premier-p550 = "dev/kernel/v6.6-hifive-premier-p550"

SRCREV_machine ?= "f1f149f03e47d9dbc11a8c53421de81eebb048eb"
SRCREV_machine:hifive-premier-p550 = "f1f149f03e47d9dbc11a8c53421de81eebb048eb"
SRCREV_meta ?= "f568a941bd5dd474b795b085e0faf22a83c0401a"

KCONFIG_MODE = "--alldefconfig"

KBUILD_DEFCONFIG ?= ""
KBUILD_DEFCONFIG:hifive-premier-p550 ?= ""

KERNEL_EXTRA_FEATURES ?= ""
KERNEL_FEATURES:remove = "cfg/fs/vfat.scc"
KERNEL_FEATURES:remove = "features/debug/printk.scc"
KERNEL_FEATURES:remove = "features/kernel-sample/kernel-sample.scc"

require recipes-kernel/linux/linux-yocto.inc

SRC_URI = "git://git@github.com/sifive/linux-kernel-hf-prem.git;protocol=ssh;name=machine;branch=${KBRANCH} \
           git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=yocto-6.6;destsuffix=${KMETA} \
           file://defconfig"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
LINUX_VERSION ?= "6.6.12"
LINUX_VERSION_EXTENSION = ""

PV = "${LINUX_VERSION}+git${SRCPV}"

KMETA = "kernel-meta"
KCONF_BSP_AUDIT_LEVEL = "2"
KCONF_AUDIT_LEVEL = "2"

COMPATIBLE_MACHINE = "(hifive-premier-p550)"

SUMMARY = "RISC-V Open Source Supervisor Binary Interface (OpenSBI)"
DESCRIPTION = "OpenSBI aims to provide an open-source and extensible implementation of the RISC-V SBI specification for a platform specific firmware (M-mode) and a general purpose OS, hypervisor or bootloader (S-mode or HS-mode). OpenSBI implementation can be easily extended by RISC-V platform or System-on-Chip vendors to fit a particular hadware configuration."
HOMEPAGE = "https://github.com/riscv/opensbi"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING.BSD;md5=42dd9555eb177f35150cf9aa240b61e5"

PROVIDES += "opensbi"

require recipes-bsp/opensbi/opensbi-payloads.inc

inherit autotools-brokensep deploy

SRCREV = "a2b255b88918715173942f2c5e1f97ac9e90c877"
SRC_URI = "git://github.com/riscv/opensbi.git;branch=master;protocol=https"

SRC_URI:append = " \
	file://0001-platform-Add-ESWIN-EIC770x-platform.patch \
	file://0002-EIC770X-Added-changes-to-write-Fractional-register.patch \
	file://0003-platform-eswin-Add-eic770X-UART-driver.patch \
	file://0004-platform-eswin-Add-shutdown-and-reset-function.patch \
	file://0001-lib-sbi-Add-Function-to-configure-FSCR.patch \
"

S = "${WORKDIR}/git"

TARGET_CC_ARCH += "${LDFLAGS}"

EXTRA_OEMAKE += "PLATFORM=${RISCV_SBI_PLAT} CHIPLET="BR2_CHIPLET_1" CHIPLET_DIE_AVAILABLE="BR2_CHIPLET_1_DIE0_AVAILABLE" MEM_MODE="BR2_MEMMODE_FLAT" PLATFORM_CLUSTER_X_CORE="BR2_CLUSTER_4_CORE" PLATFORM_RISCV_ISA=rv64imafdc_zicsr_zifencei I=${D}"
# If RISCV_SBI_PAYLOAD is set then include it as a payload
EXTRA_OEMAKE:append = " ${@riscv_get_extra_oemake_image(d)}"
EXTRA_OEMAKE:append = " ${@riscv_get_extra_oemake_fdt(d)}"

# Required if specifying a custom payload
do_compile[depends] += "${@riscv_get_do_compile_depends(d)}"

do_install:append() {
	# In the future these might be required as a dependency for other packages.
	# At the moment just delete them to avoid warnings
	rm -r ${D}/include
	rm -r ${D}/lib*
	rm -r ${D}/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/payloads
}

do_deploy () {
	install -m 755 ${D}/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_payload.* ${DEPLOYDIR}/
	install -m 755 ${D}/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_jump.* ${DEPLOYDIR}/
	install -m 755 ${D}/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_dynamic.* ${DEPLOYDIR}/
}

addtask deploy before do_build after do_install

FILES:${PN} += "/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_jump.*"
FILES:${PN} += "/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_payload.*"
FILES:${PN} += "/share/opensbi/*/${RISCV_SBI_PLAT}/firmware/fw_dynamic.*"

COMPATIBLE_HOST = "(riscv64|riscv32).*"
INHIBIT_PACKAGE_STRIP = "1"

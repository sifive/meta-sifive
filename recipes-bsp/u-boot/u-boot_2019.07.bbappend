FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append_freedom-u540 = " \
	file://U-Boot-v10-3-4-riscv-sifive-fu540-Sync-up-config-header-with-RISC-V-QEMU-support.patch \
        file://mmc-boot.txt \
	"

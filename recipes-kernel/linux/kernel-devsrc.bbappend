do_install_append_riscv64() {
	# now copy in parts from the build that we'll need later
	(
		cd ${B}

		if [ "${ARCH}" = "riscv" ]; then
		    cp -a --parents arch/riscv/include/generated $kerneldir/build/
		fi
	)	

	# now grab the chunks from the source tree that we need
	(
		cd ${S}

		if [ "${ARCH}" = "riscv" ]; then
		    cp -a --parents arch/riscv/kernel/module.lds $kerneldir/build/ || :
		fi
	)

	chown -R root:root ${D}
}

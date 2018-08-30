package org.jetbrains.kotlin.builtins.konan

import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.descriptors.findClassAcrossModuleDependencies
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.DescriptorUtils.COROUTINES_PACKAGE_FQ_NAME_RELEASE
import org.jetbrains.kotlin.storage.StorageManager

class KonanBuiltIns(storageManager: StorageManager) : KotlinBuiltIns(storageManager) {

    override fun getSuspendFunction(parameterCount: Int) =
            builtInsModule.findClassAcrossModuleDependencies(
                    ClassId(
                            COROUTINES_PACKAGE_FQ_NAME_RELEASE,
                            Name.identifier("SuspendFunction$parameterCount")
                    )
            )!!
}

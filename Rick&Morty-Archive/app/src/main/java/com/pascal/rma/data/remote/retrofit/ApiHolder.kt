package com.pascal.rma.data.remote.retrofit

import javax.inject.Inject

/**
 * Created by dronpascal on 05.10.2021.
 */
class ApiHolder @Inject constructor(
    override val rmApiService: RmApiService
) : IApiHolder
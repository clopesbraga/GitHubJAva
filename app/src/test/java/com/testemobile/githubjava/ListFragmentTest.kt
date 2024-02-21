package com.testemobile.githubjava;

import com.testemobile.githubjava.View.Adapter.ListAdpter
import com.testemobile.githubjava.Model.GitHubRepo
import com.testemobile.githubjava.NetWork.RequestRepoEndpoint
import com.testemobile.githubjava.View.ListFragment
import io.reactivex.Maybe
import org.junit.Assert
import org.junit.Before
import org.junit.Test;
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit

class ListFragmentTest {

    private var page = "1"
    @Mock
    private lateinit var retrofit: Retrofit

    @Mock
    private lateinit var requestRepoEndpoint: RequestRepoEndpoint

    @Mock
    private lateinit var adapter : ListAdpter

    @Mock
    private lateinit var gitHubRepo: Maybe<GitHubRepo>

    @Mock
    private lateinit var listFragment: ListFragment

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)

        `when`(retrofit.create(RequestRepoEndpoint::class.java)).thenReturn(requestRepoEndpoint)
        `when`(requestRepoEndpoint.getItems(Mockito.anyString())).thenReturn(gitHubRepo)

    }

    @Test
    fun should_return_success_when_send_request(){

         Assert.assertTrue(listFragment.chargeListOfRepo(page)).thenReturn(true)

    }

    @Test
    fun should_return_error_when_send_request(){

        `when`(listFragment.chargeListOfRepo(page)).thenReturn(false)
        Assert.assertFalse(listFragment.chargeListOfRepo(page))
        Assert.assertEquals(listFragment.chargeListOfRepo(page), R.string.list_repositorios_error,)


    }

}




